package com.example.stadiumcommentapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.stadiumcommentapp.databinding.FragmentHomeDateDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDateDetailFragment : Fragment() {

    private lateinit var viewModel: HomeDateDetailViewModel
    private var _binding: FragmentHomeDateDetailBinding? = null

    private val binding get() = _binding!!

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
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {
        val args: HomeDateDetailFragmentArgs by navArgs()
        val fullDate = args.date
        viewModel.setDate(fullDate)
    }

    private fun renderUi() {
        with(binding) {
            homeDateDetailTitle.setBackArrow(true)
        }
    }

    private fun observe() {
        with(viewModel) {
            date.observe(viewLifecycleOwner, Observer {
                binding.homeDateDetailTitle.setTitle("${it.year}년 ${it.month}월 ${it.date}일")
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}