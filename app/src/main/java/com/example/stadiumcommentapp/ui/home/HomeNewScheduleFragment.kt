package com.example.stadiumcommentapp.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.data.model.DateInfo
import com.example.stadiumcommentapp.data.model.WatchType
import com.example.stadiumcommentapp.databinding.FragmentHomeNewScheduleBinding
import com.example.stadiumcommentapp.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNewScheduleFragment : Fragment() {

    private lateinit var viewModel: HomeNewScheduleViewModel
    private var _binding: FragmentHomeNewScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[HomeNewScheduleViewModel::class.java]

        _binding = FragmentHomeNewScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: HomeNewScheduleFragmentArgs by navArgs()
        val fullDate = args.date

        loadData(fullDate)
        renderUi()
        observe()
    }

    private fun loadData(fullDate: String) {
        viewModel.setDate(fullDate)
    }

    private fun renderUi() {
        with(binding) {
            newScheduleTitle.setBackArrow(true)
            newScheduleTitle.setTitle("새 일정 추가")

            val selectTeamBottomSheet = SelectTeamBottomSheet()

            selectHomeTeam.setOnClickListener {
                viewModel.setIsHome(true)
                selectTeamBottomSheet.show(parentFragmentManager, selectTeamBottomSheet.tag)
            }

            selectAwayTeam.setOnClickListener {
                viewModel.setIsHome(false)
                selectTeamBottomSheet.show(parentFragmentManager, selectTeamBottomSheet.tag)
            }

            selectDate.setOnClickListener {
                viewModel.onClickSelectDate(
                    DateInfo(
                        viewModel.date.value!!.year,
                        viewModel.date.value!!.month,
                        viewModel.date.value!!.date
                    )
                )
            }

            selectWatchHome.setOnClickListener {
                viewModel.onClickType(WatchType.HOME)
            }

            selectWatchStadium.setOnClickListener {
                viewModel.onClickType(WatchType.STADIUM)
            }
        }
    }

    private fun observe() {
        with(viewModel) {
            date.observe(viewLifecycleOwner, Observer {
                binding.selectDate.text = "${it.year}-${it.month}-${it.date}"
            })

            homeTeam.observe(viewLifecycleOwner, Observer {
                binding.selectHomeTeam.text = it
                setStadiumInfo(it)
            })

            awayTeam.observe(viewLifecycleOwner, Observer {
                binding.selectAwayTeam.text = it
            })

            stadiumName.observe(viewLifecycleOwner, Observer {
                binding.selectStadiumInfo.text = it
            })

            eventSelectDate.observe(viewLifecycleOwner, EventObserver {
                val dialog =
                    DatePickerDialog(
                        requireContext(),
                        { _, year, month, date -> viewModel.setDate("${year}-${month}-${date}") },
                        it.year.toInt(),
                        it.month.toInt(),
                        it.date.toInt()
                    )

                dialog.show()
            })

            watchType.observe(viewLifecycleOwner, Observer {
                when (watchType.value) {
                    WatchType.HOME -> {
                        binding.selectWatchHome.setBackgroundResource(R.drawable.new_schedule_submit_background)
                        binding.selectWatchStadium.setBackgroundResource(R.drawable.new_schedule_background)
                    }
                    WatchType.STADIUM -> {
                        binding.selectWatchHome.setBackgroundResource(R.drawable.new_schedule_background)
                        binding.selectWatchStadium.setBackgroundResource(R.drawable.new_schedule_submit_background)
                    }
                    else -> {
                        binding.selectWatchHome.setBackgroundResource(R.drawable.new_schedule_background)
                        binding.selectWatchStadium.setBackgroundResource(R.drawable.new_schedule_background)
                    }
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}