package com.example.stadiumcommentapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stadiumcommentapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var calendarAdapter: HomeCalendarAdapter
    private lateinit var calendarView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
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
        homeViewModel.loadInfo()
        homeViewModel.setCalendar()
    }

    private fun renderUi() {

        calendarView = binding.calendarRecycler
        calendarAdapter = HomeCalendarAdapter()
        calendarView.layoutManager =
            StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarView.adapter = calendarAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun observe() {
        with(homeViewModel) {
            calendarList.observe(viewLifecycleOwner, Observer {
                calendarAdapter.submitList(it)
            })

            interestStadium.observe(viewLifecycleOwner, Observer {
                binding.stadiumName.text = it.stadiumName
                binding.stadiumAddress.text = it.stadiumAddress
                binding.stadiumPhoneNum.text = it.stadiumPhone
            })

            thisMonth.observe(viewLifecycleOwner, Observer {
                binding.textMonth.text = it + "월"
            })

            thisYear.observe(viewLifecycleOwner, Observer {
                binding.textYear.text = it + "년"
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}