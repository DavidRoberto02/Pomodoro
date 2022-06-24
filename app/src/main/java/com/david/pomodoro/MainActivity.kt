package com.david.pomodoro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.david.pomodoro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //load image animation
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_atg)
        val animBtnStart = AnimationUtils.loadAnimation(this, R.anim.anim_btn_start)
        val animBtnFinish = AnimationUtils.loadAnimation(this, R.anim.anim_btn_finish)

        binding.ivImagePinguin.startAnimation(anim)
        binding.tvPhrasePomodoroTitle.startAnimation(animBtnStart)
        binding.tvPhrasePomodoroSubtitle.startAnimation(animBtnStart)
        binding.btnStart.startAnimation(animBtnFinish)

        binding.btnStart.setOnClickListener {
            initSession()
        }
    }

    private fun initSession() {
        val intent = Intent(this, StopWatchActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}