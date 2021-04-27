package com.example.lesson_9.domain

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class BackgroundService: Service() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable
    override fun onBind(intent: Intent?): IBinder? {
        //TODO("Not yet implemented")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(applicationContext, "Service started", Toast.LENGTH_SHORT).show()
        mHandler = Handler()
        mRunnable = Runnable { download() }
        mHandler.postDelayed(mRunnable, 5000) //минута - 60000
        return START_STICKY
    }

    private fun download() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Service destroyed", Toast.LENGTH_SHORT).show()
        mHandler.removeCallbacks(mRunnable)
    }
}