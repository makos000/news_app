package com.example.news_app.data.firebaseauth

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}