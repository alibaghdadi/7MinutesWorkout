package com.example.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.lang.Exception
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var isFirst = true
    private val rest = "Rest\n\nUPCOMING EXERCISE\n"
    private val ready = "GET READY FOR\n\nUPCOMING EXERCISE\n"

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

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()

        binding.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
        setRestProgressBar()
    }

    private fun setupRestView() {

        playSounds()

        binding.flExerciseProgressBar.visibility = View.GONE
        binding.ivModel.visibility = View.GONE

        val tvConstraint = binding.tvTitle.layoutParams as ConstraintLayout.LayoutParams
        tvConstraint.bottomToTop = binding.flRestProgressBar.id
        tvConstraint.topToBottom = ConstraintLayout.LayoutParams.UNSET
        binding.tvTitle.requestLayout()

        binding.tvTitle.text = buildString {
            if (isFirst)
                append(ready)
            else
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

        speakOut(exerciseList!![currentExercisePosition].getName() + " for thirty seconds")

        resetRestTimers()
        resetExerciseTimers()
    }

    private fun setRestProgressBar() {
        binding.restProgressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                if (restProgress == 1) {
                    if (isFirst) {
                        speakOut("Get ready, Next Exercise " + exerciseList!![currentExercisePosition + 1].getName())
                        isFirst = false
                    } else
                        speakOut("Take a rest, Next Exercise " + exerciseList!![currentExercisePosition + 1].getName())
                }
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

                    speakOut("Congratulation!! You have completed the 7 minutes workout.")

                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulation!! You have completed the 7 minutes workout.",
                        Toast.LENGTH_LONG
                    ).show()

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

        if (player != null) {
            player!!.stop()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            //set US English as language for tts
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initializing Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun playSounds() {
        try {
            val soundURI =
                Uri.parse("android.resource://com.example.a7minutesworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}