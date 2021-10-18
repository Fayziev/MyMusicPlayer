package uz.xsoft.mymusicplayer.service

import androidx.lifecycle.MutableLiveData
import uz.xsoft.mymusicplayer.data.MusicData

object EventBus {
    val eventLiveData = MutableLiveData<ActionEnum>()

    lateinit var data : MusicData
}