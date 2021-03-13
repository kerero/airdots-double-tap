package com.orik.airdotsdoubletap

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val prefs = getSharedPreferences(
            this.applicationContext.packageName, MODE_PRIVATE
        )
        val editor = prefs.edit()
        R.id.swtchNoRewind
        swtchNoRewind.isChecked = prefs.getBoolean(getString(R.string.PREF_NO_REWIND), false)
        swtchNoRewind.setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean(getString(R.string.PREF_NO_REWIND), isChecked)
            editor.apply()
        }
    }
}