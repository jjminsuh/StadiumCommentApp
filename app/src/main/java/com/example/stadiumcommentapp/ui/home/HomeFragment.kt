package com.example.stadiumcommentapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stadiumcommentapp.databinding.FragmentHomeBinding
import com.example.stadiumcommentapp.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var calendarAdapter: HomeCalendarAdapter
    private lateinit var calendarView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {
        viewModel.loadInfo()
        viewModel.setCalendar()
    }

    private fun renderUi() {

        with(binding) {
            homeTitle.setTitle("Home")
            homeTitle.setBackArrow(false)

            val width = resources.displayMetrics.widthPixels - (16 * resources.displayMetrics.density + 0.5).toInt()
            textSunday.layoutParams.width = width / 7
            textMonday.layoutParams.width = width / 7
            textTuesday.layoutParams.width = width / 7
            textWednesday.layoutParams.width = width / 7
            textThursday.layoutParams.width = width / 7
            textFriday.layoutParams.width = width / 7
            textSaturday.layoutParams.width = width / 7
        }

        calendarView = binding.calendarRecycler
        calendarAdapter = HomeCalendarAdapter(object : DateDetailListener {
            override fun onClickDate(date: String) {
                viewModel.onClickDate("${viewModel.thisYear.value}-${viewModel.thisMonth.value}-${date}")
            }
        })
        calendarView.layoutManager =
            StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarView.adapter = calendarAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        with(viewModel) {
            calendarList.observe(viewLifecycleOwner, Observer {
                calendarAdapter.submitList(it)
            })

            interestStadium.observe(viewLifecycleOwner, Observer {
                binding.stadiumName.text = it.stadiumName
                binding.stadiumAddress.text = it.stadiumAddress
            })

            thisMonth.observe(viewLifecycleOwner, Observer {
                binding.textMonth.text = it + "월"
            })

            thisYear.observe(viewLifecycleOwner, Observer {
                binding.textYear.text = it + "년"
            })

            eventDateClick.observe(viewLifecycleOwner, EventObserver {
                val action =
                    HomeFragmentDirections.actionNavigationHomeToNavigationHomeDateDetail(it)
                Navigation.findNavController(requireView()).navigate(action)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}