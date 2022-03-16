package com.example.stadiumcommentapp.ui.profile

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.data.ReviewListItem

class ProfileMyReviewListAdapter(private val context: Context): RecyclerView.Adapter<ProfileMyReviewListAdapter.ViewHolder>() {

    var myReviewList = ArrayList<ReviewListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun MyReviewList(reviewList: ArrayList<ReviewListItem>){
        TODO("Not yet implemented")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val title: TextView = itemView.findViewById(R.id.my_review_title)
        private val nickname: TextView = itemView.findViewById(R.id.my_review_nickname)
        private val writeDate: TextView = itemView.findViewById(R.id.my_review_write_date)
        private val stadiumName: TextView = itemView.findViewById(R.id.my_review_stadium_name)
        private val stadiumArea: TextView = itemView.findViewById(R.id.my_review_stadium_area)
        private val gameDate: TextView = itemView.findViewById(R.id.my_review_game_date)

        fun bind(reviewItem: ReviewListItem){
            title.text = reviewItem.title
            nickname.text = reviewItem.nickname
            writeDate.text = reviewItem.writeDate
            stadiumName.text = reviewItem.stadiumName
            stadiumArea.text = reviewItem.stadiumArea
            gameDate.text = reviewItem.gameDate
        }

    }
}