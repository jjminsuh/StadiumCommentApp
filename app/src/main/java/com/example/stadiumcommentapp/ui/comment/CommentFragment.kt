package com.example.stadiumcommentapp.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.databinding.FragmentCommentBinding
import com.example.stadiumcommentapp.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {

    private lateinit var viewModel: CommentViewModel
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CommentViewModel::class.java]

        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {
        viewModel.loadStadiumList()
    }

    private fun renderUi() {
        with(binding) {
            buttonSubmitComment.setOnClickListener {
                viewModel.onClickSubmitComment()
            }
        }
    }

    private fun observe() {
        with(viewModel) {
            stadiumArray.observe(viewLifecycleOwner, Observer {
                binding.selectStadium.adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    it
                )
                //onItemSelectedListener
            })

            eventSubmitComment.observe(viewLifecycleOwner, EventObserver {
                val action = CommentFragmentDirections.actionNavigationCommentToNavigationCommentNew()
                Navigation.findNavController(requireView()).navigate(action)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
