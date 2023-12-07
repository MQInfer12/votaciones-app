package com.example.finalapp.utils

import android.app.Application

class Global: Application() {
    companion object {
        var ci = "0"
    }

    override fun onCreate() {
        super.onCreate()
        // initialization code here
    }
}