package com.example.stadiumcommentapp.util

import androidx.lifecycle.Observer

open class Event<T> (private val content: T) {

    private var isHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

    fun peekContent() : T = content
}

class EventObserver<T> (private val onEventUnHandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnHandledContent(value)
        }
    }
}
