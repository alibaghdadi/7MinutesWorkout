package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarExercise)

        // To add the back button in the toolbar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestProgressBar()

        val exerciseModel: ExerciseModel


    }

    private fun setupRestView() {

        val tvConstraint = binding.tvTitle.layoutParams as ConstraintLayout.LayoutParams
        tvConstraint.bottomToTop = binding.flRestProgressBar.id
        binding.tvTitle.requestLayout()

        binding.flExerciseProgressBar.visibility = View.GONE
        binding.tvTitle.text = "Rest"
        binding.flRestProgressBar.visibility = View.VISIBLE

        resetRestTimers()
        resetExerciseTimers()
    }

    private fun setupExerciseView() {

        val tvConstraint = binding.tvTitle.layoutParams as ConstraintLayout.LayoutParams
        tvConstraint.bottomToTop = binding.flExerciseProgressBar.id
        binding.tvTitle.requestLayout()

        binding.flRestProgressBar.visibility = View.GONE
        binding.tvTitle.text = "Exercise Name"
        binding.flExerciseProgressBar.visibility = View.VISIBLE

        resetRestTimers()
        resetExerciseTimers()

    }

    private fun setRestProgressBar() {
        binding.restProgressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                binding.restProgressBar.progress = 10 - restProgress
                binding.tvRestTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                setupExerciseView()
                setExerciseProgressBar()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        binding.exerciseProgressBar.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.exerciseProgressBar.progress = 30 - exerciseProgress
                binding.tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                setupRestView()
                setRestProgressBar()
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        resetRestTimers()
        resetExerciseTimers()
    }

    private fun resetRestTimers() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
    }

    private fun resetExerciseTimers() {
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
    }

}