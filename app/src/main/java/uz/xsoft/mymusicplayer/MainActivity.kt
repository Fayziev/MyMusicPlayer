package uz.xsoft.mymusicplayer

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main)


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,ForegroundService::class.java)

        findViewById<Button>(R.id.startService).setOnClickListener {
            val bundle= Bundle()
            bundle.putSerializable("data",ActionEnum.PLAY)
            intent.putExtras(bundle)
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent)
            } else startService(intent)
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(intent)
        }

        EventBus.eventLiveData.observe(this, Observer<ActionEnum> {
            Toast.makeText(this,it.name,Toast.LENGTH_SHORT).show()
        })
        /*
         var count = 0
        val intent = Intent(this,ForegroundService::class.java)

        findViewById<Button>(R.id.startService).setOnClickListener {
            val bundle= Bundle()
            bundle.putString("data","${count ++}")
            intent.putExtras(bundle)
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent)
            } else startService(intent)
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(intent)
        }
        * */

        /*
        val intent = Intent(this,BackgroundService::class.java)

        findViewById<Button>(R.id.startService).setOnClickListener {
            val bundle= Bundle()
            bundle.putString("data","${count ++}")
            intent.putExtras(bundle)
            startService(intent)
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            stopService(intent)
        }*/
    }
}*/