package com.roman.yarullin.moviesdatabase.presentation

class Event<Data>(data: Data) {
    val data: Data? = data
        get() {
            if (wasHandled) return null
            wasHandled = true
            return field
        }
    private var wasHandled: Boolean = false
}