package uz.xsoft.mymusicplayer.presentation.ui.screen

import android.Manifest
import android.content.Intent
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.xsoft.mymusicplayer.R
import uz.xsoft.mymusicplayer.databinding.ScreenPlayListBinding
import uz.xsoft.mymusicplayer.presentation.ui.adapter.PlayListAdapter
import uz.xsoft.mymusicplayer.presentation.viewmodel.PlayListViewModel
import uz.xsoft.mymusicplayer.service.ActionEnum
import uz.xsoft.mymusicplayer.service.EventBus
import uz.xsoft.mymusicplayer.service.ForegroundService
import uz.xsoft.mymusicplayer.utils.checkPermissions
import uz.xsoft.mymusicplayer.utils.scope

@AndroidEntryPoint
class PlayListScreen : Fragment(R.layout.screen_play_list) {
    private val binding by viewBinding(ScreenPlayListBinding::bind)
    private val viewModel by viewModels<PlayListViewModel>()
    private val adapter = PlayListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        playList.layoutManager = LinearLayoutManager(requireContext())
        playList.adapter = adapter

        adapter.setEventMusicListener {
            val intent = Intent(requireActivity(), ForegroundService::class.java)
            EventBus.data = it
            intent.putExtra("command", ActionEnum.PLAY)
            if (Build.VERSION.SDK_INT >= 26) {
                requireActivity().startForegroundService(intent)
            } else requireActivity().startService(intent)
        }

        viewModel.playListLiveData.observe(viewLifecycleOwner, playListObserver)
        requireActivity().checkPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            viewModel.loadMusics()
        }
    }

    private val playListObserver = Observer<Cursor> {
        adapter.cursor = it
        adapter.notifyDataSetChanged()
    }
}