package com.example.stadiumcommentapp.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.R
import com.example.stadiumcommentapp.data.ReviewListItem

class ProfileMyReviewListAdapter(private val context: Context): RecyclerView.Adapter<ProfileMyReviewListAdapter.ViewHolder>() {

    var myReviewList = ArrayList<ReviewListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_review_item, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myReviewList[position])
    }

    override fun getItemCount(): Int = myReviewList.size

    fun MyReviewList(reviewList: ArrayList<ReviewListItem>){
        myReviewList = reviewList
        notifyDataSetChanged()
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