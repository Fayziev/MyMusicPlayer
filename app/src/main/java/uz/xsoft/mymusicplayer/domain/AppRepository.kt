package uz.xsoft.mymusicplayer.domain

import android.database.Cursor
import kotlinx.coroutines.flow.Flow
import uz.xsoft.mymusicplayer.app.App
import uz.xsoft.mymusicplayer.utils.getMusicListCursor
import javax.inject.Inject

class AppRepository @Inject constructor() {

    fun loadMusics() : Flow<Cursor> = App.instance.getMusicListCursor()
}