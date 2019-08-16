package com.orik.airdotsdoubletap

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlin.system.exitProcess
import android.view.KeyEvent
import android.os.SystemClock
import android.media.AudioManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        skipTrack(this.applicationContext)

        finish()
        exitProcess(0)
    }

    private fun skipTrack(appContext: Context) {
        val mAudioManager = appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val eventTime = SystemClock.uptimeMillis()

        val downEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_NEXT, 0)
        mAudioManager.dispatchMediaKeyEvent(downEvent)

        val upEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_NEXT, 0)
        mAudioManager.dispatchMediaKeyEvent(upEvent)
    }
}
