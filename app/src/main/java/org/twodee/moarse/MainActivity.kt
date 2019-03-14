package org.twodee.moarse

import android.app.Activity
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class MainActivity : Activity() {
  private lateinit var dashButton: Button
  private lateinit var dotButton: Button
  private lateinit var handler: Handler

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    dashButton = findViewById(R.id.dash)
    dotButton = findViewById(R.id.dot)
    val toner = ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
    handler = Handler()

    val dotTime = 300
    val dashTime = 3 * dotTime

    dashButton.setOnClickListener {
      toner.startTone(ToneGenerator.TONE_SUP_DIAL, dashTime)
      disableButtons()
      handler.postDelayed(::enableButtons, dashTime.toLong())
    }

    dotButton.setOnClickListener {
      toner.startTone(ToneGenerator.TONE_SUP_DIAL, dotTime)
      disableButtons()
      handler.postDelayed(::enableButtons, dotTime.toLong())
    }
  }

  private fun disableButtons() {
    dashButton.isEnabled = false
    dotButton.isEnabled = false
  }

  private fun enableButtons() {
    dashButton.isEnabled = true
    dotButton.isEnabled = true
  }
}
