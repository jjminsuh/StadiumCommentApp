package com.example.stadiumcommentapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stadiumcommentapp.databinding.BottomSheetSelectTeamBinding
import com.example.stadiumcommentapp.util.EventObserver
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTeamBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: HomeNewScheduleViewModel
    private var _binding: BottomSheetSelectTeamBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectTeamAdapter: SelectTeamAdapter
    private lateinit var teamView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[HomeNewScheduleViewModel::class.java]

        _binding = BottomSheetSelectTeamBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {
        viewModel.loadTeamList()
    }

    private fun renderUi() {
        teamView = binding.recyclerViewTeamName
        selectTeamAdapter = SelectTeamAdapter(object  : SelectTeamListener {
            override fun onClickTeam(team: String) {
                viewModel.onClickTeamBottomSheet(team)
            }
        })
        teamView.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        teamView.adapter = selectTeamAdapter
    }

    private fun observe() {
        with(viewModel) {
            teamArray.observe(viewLifecycleOwner, Observer {
                selectTeamAdapter.submitList(it)
            })

            eventSelectTeamBottomSheet.observe(viewLifecycleOwner, EventObserver{
                if(isHome.value == true) {
                    viewModel.setHomeTeam(it)
                } else {
                    viewModel.setAwayTeam(it)
                }

                dismiss()
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
