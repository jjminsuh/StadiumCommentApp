package com.example.stadiumcommentapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stadiumcommentapp.R

class HomeCalendarAdapter(private val context: Context) : RecyclerView.Adapter<HomeCalendarAdapter.ViewHolder>() {

    var dateList = ArrayList<String> ()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_calendar_date, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dateList[position])
    }

    override fun getItemCount(): Int = dateList.size

    fun dateList(calendarList: ArrayList<String>) {
        dateList = calendarList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val date: TextView = itemView.findViewById(R.id.date_text)

        fun bind(singleDate: String){
            if(singleDate == "EMPTY"){
                date.text = ""
            }
            else{
                date.text = singleDate
            }
        }
    }
}