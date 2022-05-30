package com.example.stadiumcommentapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var myReviewView: RecyclerView
    private lateinit var myReviewListAdapter: ProfileMyReviewListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        renderUi()
        observe()
    }

    private fun loadData() {
        profileViewModel.loadMyReview()
    }

    private fun renderUi() {
        myReviewView = binding.myReviewList
        myReviewListAdapter = ProfileMyReviewListAdapter(requireContext())

        myReviewView.layoutManager = LinearLayoutManager(context)
        myReviewView.adapter = myReviewListAdapter
    }

    private fun observe() {
        with(profileViewModel) {
            myReviewList.observe(viewLifecycleOwner, Observer {
                myReviewListAdapter.MyReviewList(it)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}