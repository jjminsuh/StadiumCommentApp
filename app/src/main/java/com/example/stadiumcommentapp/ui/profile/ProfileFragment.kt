package com.example.stadiumcommentapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.data.ReviewListItem
import com.example.stadiumcommentapp.databinding.FragmentHomeBinding
import com.example.stadiumcommentapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //test data
        var test = ArrayList<ReviewListItem> ()

        for(i in 0 until 10){
            test.add(ReviewListItem("잘 보이는 편이예요.", "kt소닉붐화이팅", "2022-03-08", "수원 KT 소닉붐 아레나","D4", "2022-02-11"))
        }

        val myReviewView = root.findViewById<RecyclerView>(R.id.my_review_list)
        val myReviewListAdapter = ProfileMyReviewListAdapter(requireContext())

        myReviewView.layoutManager = LinearLayoutManager(context)
        myReviewListAdapter.MyReviewList(test)
        myReviewView.adapter = myReviewListAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}