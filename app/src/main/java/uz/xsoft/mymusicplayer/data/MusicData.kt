package uz.xsoft.mymusicplayer.data

import android.net.Uri
import java.io.Serializable

data class MusicData(
    val id : Int,
    val artist : String?,
    val title : String?,
    val data : String?,
    val displayName : String?,
    val duration : Long?,
    val imageUri : Uri?
) : Serializable