package uz.xsoft.mymusicplayer.service

import androidx.lifecycle.MutableLiveData

object EventBus {
    val eventLiveData = MutableLiveData<ActionEnum>()
}