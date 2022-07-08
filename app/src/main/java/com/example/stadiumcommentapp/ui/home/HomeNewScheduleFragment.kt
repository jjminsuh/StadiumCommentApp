package com.example.stadiumcommentapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stadiumcommentapp.databinding.FragmentHomeNewScheduleBinding
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
        viewModel = ViewModelProvider(this)[HomeNewScheduleViewModel::class.java]

        _binding = FragmentHomeNewScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {

    }

    private fun renderUi() {

    }

    private fun observe() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}