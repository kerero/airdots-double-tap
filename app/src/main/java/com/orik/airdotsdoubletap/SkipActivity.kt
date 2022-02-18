package com.orik.airdotsdoubletap


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.system.exitProcess
import android.view.KeyEvent
import android.os.SystemClock
import android.media.AudioManager
import androidx.preference.PreferenceManager

class SkipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        skipTrack(this.applicationContext)

        finish()
        exitProcess(0)
    }

    private fun skipTrack(appContext: Context) {
        val mAudioManager = appContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val eventTime = SystemClock.uptimeMillis()

        if(!mAudioManager.isMusicActive) {
            pressKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS, mAudioManager, eventTime)
            val prefs = PreferenceManager.getDefaultSharedPreferences(appContext)
            // Skip to previous song instead of rewinding current one if not at song start
            if(prefs.getBoolean(getString(R.string.PREF_skip_backwards_twice), false)) {
                pressKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS, mAudioManager, eventTime)
            } else {
                pressKey(KeyEvent.KEYCODE_MEDIA_PLAY, mAudioManager, eventTime)
            }
        } else {
            pressKey(KeyEvent.KEYCODE_MEDIA_NEXT, mAudioManager, eventTime)

        }
    }

    private fun pressKey(keyCode: Int, audioManager: AudioManager, eventTime: Long) {
        val downEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, keyCode, 0)
        audioManager.dispatchMediaKeyEvent(downEvent)

        val upEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, keyCode, 0)
        audioManager.dispatchMediaKeyEvent(upEvent)
    }
}