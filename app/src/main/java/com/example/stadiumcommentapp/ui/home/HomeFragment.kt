package com.example.stadiumcommentapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val today = GregorianCalendar()

        val calendarList = ArrayList<String> ()

        try {
            val firstDayOfMonth = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 1, 0, 0, 0)

            val firstDay = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
            val lastDay = firstDayOfMonth.getActualMaximum(Calendar.DATE) + 1

            for(i in 0 until firstDay){
                calendarList.add("EMPTY")
            }

            for(i in 1 until lastDay){
                calendarList.add(i.toString())
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            Log.d("Error", e.toString())
        }

        val calendarView = root.findViewById<RecyclerView>(R.id.calendar_recycler)
        val calendarAdapter = HomeCalendarAdapter(requireContext())

        calendarView.layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarAdapter.dateList(calendarList)
        calendarView.adapter = calendarAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}