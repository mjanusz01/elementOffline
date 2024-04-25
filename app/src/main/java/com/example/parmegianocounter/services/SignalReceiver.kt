package com.example.parmegianocounter.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SignalReceiver(
    val onMentioned: () -> Unit
): BroadcastReceiver(){

    override fun onReceive(p0: Context?, p1: Intent?) {
        onMentioned()
    }

}