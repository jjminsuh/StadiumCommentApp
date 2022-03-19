package com.example.stadiumcommentapp.ui.write

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
import com.example.stadiumcommentapp.databinding.FragmentWriteBinding
import com.example.stadiumcommentapp.ui.profile.ProfileMyReviewListAdapter

class WriteFragment : Fragment() {

    private lateinit var writeViewModel: WriteViewModel
    private var _binding: FragmentWriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        writeViewModel =
            ViewModelProvider(this).get(WriteViewModel::class.java)

        _binding = FragmentWriteBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}