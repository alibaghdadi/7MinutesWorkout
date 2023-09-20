package com.example.a7minutesworkout

import android.content.Intent
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

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private val rest = "Rest\n\nUPCOMING EXERCISE\n"

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

        exerciseList = Constants.defaultExerciseList()

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
        setRestProgressBar()
    }

    private fun setupRestView() {

        binding.flExerciseProgressBar.visibility = View.GONE
        binding.ivModel.visibility = View.GONE

        val tvConstraint = binding.tvTitle.layoutParams as ConstraintLayout.LayoutParams
        tvConstraint.bottomToTop = binding.flRestProgressBar.id
        tvConstraint.topToBottom = ConstraintLayout.LayoutParams.UNSET
        binding.tvTitle.requestLayout()

        binding.tvTitle.text = buildString {
            append(rest)
            append(exerciseList!![currentExercisePosition + 1].getName())
        }
        binding.flRestProgressBar.visibility = View.VISIBLE

        resetRestTimers()
        resetExerciseTimers()
    }

    private fun setupExerciseView() {

        binding.flRestProgressBar.visibility = View.GONE
        binding.ivModel.visibility = View.VISIBLE

        val tvConstraint = binding.tvTitle.layoutParams as ConstraintLayout.LayoutParams
        tvConstraint.bottomToTop = binding.flExerciseProgressBar.id
        tvConstraint.topToBottom = binding.ivModel.id

        binding.tvTitle.requestLayout()

        binding.tvTitle.text = exerciseList!![currentExercisePosition].getName()
        binding.ivModel.setBackgroundResource(exerciseList!![currentExercisePosition].getImage())
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
                currentExercisePosition++
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
                if (currentExercisePosition == exerciseList?.size?.minus(1)) {
                    val intent = Intent(this@ExerciseActivity, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } else {
                    setupRestView()
                    setRestProgressBar()
                }
            }

        }.start()
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

    override fun onDestroy() {
        super.onDestroy()
        resetRestTimers()
        resetExerciseTimers()
    }

}