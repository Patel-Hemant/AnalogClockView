package com.hemantpatel.analogclockview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hemantpatel.clock_library.AnalogClockView

class MainActivity : AppCompatActivity() {
    private val mClockView: AnalogClockView
        get() = findViewById(R.id.clock_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        mClockView.startClock()
    }

    override fun onStop() {
        super.onStop()
        mClockView.stopClock()
    }
}