package com.example.stadiumcommentapp.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stadiumcommentapp.databinding.TitleBarViewBinding

open class TitleBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding = TitleBarViewBinding.inflate(LayoutInflater.from(context), this, true)

    open fun setTitle(title: String) {
        binding.textTitle.text = title
    }

    open fun setBackArrow(visible: Boolean) {
        binding.imageBack.visibility = if(visible) View.VISIBLE else View.GONE
    }
}