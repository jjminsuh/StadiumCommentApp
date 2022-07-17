package com.example.stadiumcommentapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.data.model.ScheduleInfo
import com.example.stadiumcommentapp.databinding.HomeDateDetailScheduleItemBinding
import javax.inject.Inject

class HomeDateDetailScheduleAdapter @Inject constructor() :
    ListAdapter<ScheduleInfo, HomeDateDetailScheduleAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeDateDetailScheduleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: HomeDateDetailScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ScheduleInfo) {
            with(binding) {
                scheduleTitle.text = item.scheduleTitle
                scheduleTime.text = item.time
                scheduleStadium.text = item.stadium
                scheduleType.text = item.type
                scheduleMemo.text = item.memo
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ScheduleInfo>() {
            override fun areItemsTheSame(oldItem: ScheduleInfo, newItem: ScheduleInfo): Boolean {
                return oldItem.scheduleTitle == newItem.scheduleTitle
            }

            override fun areContentsTheSame(oldItem: ScheduleInfo, newItem: ScheduleInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
