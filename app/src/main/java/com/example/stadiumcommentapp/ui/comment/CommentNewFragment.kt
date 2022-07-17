package com.example.stadiumcommentapp.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stadiumcommentapp.MainActivity
import com.example.stadiumcommentapp.databinding.FragmentCommentNewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentNewFragment : Fragment() {

    private lateinit var viewModel: CommentNewViewModel
    private var _binding: FragmentCommentNewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNav(true)

        viewModel = ViewModelProvider(this)[CommentNewViewModel::class.java]

        _binding = FragmentCommentNewBinding.inflate(inflater, container, false)
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

    override fun onDestroy() {
        super.onDestroy()
        val mainActivity = activity as MainActivity
        mainActivity.hideBottomNav(false)
    }
}
