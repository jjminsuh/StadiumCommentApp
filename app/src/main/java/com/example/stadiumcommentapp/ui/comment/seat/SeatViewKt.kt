package com.example.stadiumcommentapp.ui.comment.seat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stadiumcommentapp.databinding.StadiumSeatKt8Binding

class SeatViewKt @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){

    val binding = StadiumSeatKt8Binding.inflate(LayoutInflater.from(context), this, true)


}