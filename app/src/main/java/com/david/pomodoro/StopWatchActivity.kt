package com.david.pomodoro

import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.david.pomodoro.databinding.ActivityStopWatchBinding

class StopWatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStopWatchBinding

    private lateinit var countDownTimer: CountDownTimer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStartNow.setOnClickListener {
            initSesionNow()
            startPomodoro()
        }
        binding.btnFinish.alpha = 0F

        binding.btnFinish.setOnClickListener {
            pauseSession()
        }

    }

    private fun startPomodoro() {
        val minutes = binding.etMinutes.text.toString().toLong() * 60 * 1000
        val seconds = binding.etSeconds.text.toString().toLong() * 1000

        //val tiempoSegundos = binding.etSeconds.text.toString().toLong()
        val tiempoMilisegundos = seconds + minutes

        countDownTimer = object : CountDownTimer(tiempoMilisegundos, 1000) {

            override fun onTick(l: Long) {
                var tiempoSegundos = (l / 1000).toInt() + 1
                tiempoSegundos %= 3600
                val min = tiempoSegundos / 60
                tiempoSegundos %= 60
                binding.tvMinutes.text = min.toString().padStart(2, '0') + ":" +
                        tiempoSegundos.toString().padStart(2, '0')
            }

            override fun onFinish() {

                val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val r = RingtoneManager.getRingtone(this@StopWatchActivity, notification)
                r.play()
                this.cancel()
            }
        }.start()
    }

    private fun initSesionNow() {
        //animation
        val roundalone = AnimationUtils.loadAnimation(this, R.anim.round_alone_anchor)
        binding.ivAnchor.startAnimation(roundalone)
        binding.btnFinish.animate().alpha(1F).translationY(-80F).setDuration(300).start()
        binding.btnStartNow.animate().alpha(0F).setDuration(300).start()
        //init time
        /*binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()*/

    }

    private fun pauseSession() {
        //binding.chronometer.stop()
        binding.ivAnchor.clearAnimation()
        binding.btnStartNow.animate().alpha(1F).setDuration(300).start()
        countDownTimer.onFinish()
        true
    }
}
