package uz.xsoft.mymusicplayer.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        Log.d("TTT","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.extras?.getString("data","")
        Log.d("TTT",data!!)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("TTT","onDestroy")
    }
}



