package com.example.stadiumcommentapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.databinding.HomeCalendarDateBinding
import javax.inject.Inject

class HomeCalendarAdapter @Inject constructor(private val dateListener: DateDetailListener) :
    ListAdapter<String, HomeCalendarAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeCalendarDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: HomeCalendarDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(singleDate: String) {
            if (singleDate == "EMPTY") {
                binding.dateText.text = ""
            } else {
                binding.dateText.text = singleDate

                binding.root.setOnClickListener {
                    dateListener.onClickDate(singleDate)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}