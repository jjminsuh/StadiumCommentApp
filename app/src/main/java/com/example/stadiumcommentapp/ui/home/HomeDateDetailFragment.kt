package com.example.stadiumcommentapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.databinding.FragmentHomeDateDetailBinding
import com.example.stadiumcommentapp.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDateDetailFragment : Fragment() {

    private lateinit var viewModel: HomeDateDetailViewModel
    private var _binding: FragmentHomeDateDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var scheduleAdapter: HomeDateDetailScheduleAdapter
    private lateinit var scheduleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[HomeDateDetailViewModel::class.java]

        _binding = FragmentHomeDateDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: HomeDateDetailFragmentArgs by navArgs()
        val fullDate = args.date

        loadData(fullDate)
        renderUi(fullDate)
        observe()
    }

    private fun loadData(fullDate: String) {
        viewModel.setDate(fullDate)
        viewModel.setList()
    }

    private fun renderUi(fullDate: String) {
        binding.homeDateDetailTitle.setBackArrow(true)
        binding.homeDateDetailTitle.binding.imageBack.setOnClickListener {
            //뒤로 가기 구현 필요
            //https://witcheryoon.tistory.com/119
        }

        scheduleView = binding.scheduleRecycler
        scheduleAdapter = HomeDateDetailScheduleAdapter()
        scheduleView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        scheduleView.adapter = scheduleAdapter

        binding.scheduleAdd.setOnClickListener {
            viewModel.onClickAddScedule(fullDate)
        }
    }

    private fun observe() {
        with(viewModel) {
            date.observe(viewLifecycleOwner, Observer {
                binding.homeDateDetailTitle.setTitle("${it.year}년 ${it.month}월 ${it.date}일")
            })

            scheduleList.observe(viewLifecycleOwner, Observer {
                scheduleAdapter.submitList(it)
            })

            eventAddSchedule.observe(viewLifecycleOwner, EventObserver {
                val action = HomeDateDetailFragmentDirections.actionNavigationHomeDateDetailToNavigationHomeNewSchedule(it)
                Navigation.findNavController(requireView()).navigate(action)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}