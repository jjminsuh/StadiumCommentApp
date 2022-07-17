package com.example.stadiumcommentapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.databinding.BottomSheetSelectTeamItemBinding
import javax.inject.Inject

class SelectTeamAdapter @Inject constructor(private val selectTeamListener: SelectTeamListener) :
    ListAdapter<String, SelectTeamAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BottomSheetSelectTeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: BottomSheetSelectTeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: String) {
            binding.teamItem.text = team

            binding.root.setOnClickListener {
                selectTeamListener.onClickTeam(team)
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
