package uz.xsoft.mymusicplayer.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import uz.xsoft.mymusicplayer.R

class ForegroundService : Service() {
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    override fun onBind(intent: Intent?): IBinder? = null
    private val CHANNEL_ID ="MY_MUSIC"
    private var _mediaPlayer : MediaPlayer? =null
    private val mediaPlayer get() = _mediaPlayer!!
    private val notification by lazy {
        NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("My music")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(createRemoteView())
            .build()
    }

    override fun onCreate() {
        Log.d("TTT","onCreate")
        createChannel()
        _mediaPlayer = MediaPlayer.create(this,R.raw.music)  // Uri
        startForeground(1,notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(CHANNEL_ID,"My music app",NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun createRemoteView() : RemoteViews {
        val remoteView = RemoteViews(this.packageName,R.layout.notification_layout)
        remoteView.setOnClickPendingIntent(R.id.playButton, createPendingIntent(ActionEnum.PLAY))
        remoteView.setOnClickPendingIntent(R.id.pauseButton, createPendingIntent(ActionEnum.PAUSE))
        remoteView.setOnClickPendingIntent(R.id.nextButton, createPendingIntent(ActionEnum.NEXT))
        remoteView.setOnClickPendingIntent(R.id.closeButton, createPendingIntent(ActionEnum.CLOSE))
        return remoteView
    }

    private fun createPendingIntent(action : ActionEnum) : PendingIntent {
        val intent = Intent(this,ForegroundService::class.java)
        intent.putExtra("data",action)
        return PendingIntent.getService(this,action.ordinal, intent,PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val command = intent?.extras?.getSerializable("data") as? ActionEnum
        doneCommand(command)
        return START_NOT_STICKY
    }

    private fun doneCommand(command : ActionEnum?) {
        when(command) {
            ActionEnum.PLAY -> {
                mediaPlayer.start()
            }

            ActionEnum.PAUSE -> {
                mediaPlayer.pause()
            }

            ActionEnum.NEXT -> {
                EventBus.eventLiveData.value = command
            }

            ActionEnum.CLOSE -> {
                mediaPlayer.stop()
                stopForeground(true)
            }
        }
    }

    override fun onDestroy() {
        _mediaPlayer = null
        scope.cancel()
        Log.d("TTT","onDestroy")
    }

}