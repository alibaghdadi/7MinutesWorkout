package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityBmiCalculatorBinding
import kotlin.math.pow

class BmiCalculator : AppCompatActivity() {
    private lateinit var binding: ActivityBmiCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarBMICalculator)

        // To add the back button in the toolbar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }

        binding.toolbarBMICalculator.setNavigationOnClickListener {
            val exitDialogFragment = ExitDialogFragment()
            exitDialogFragment.show(supportFragmentManager, "MyDialog")
        }

        binding.btnBMI.setOnClickListener {

            val weight = binding.edMetricUnitWeight.text.toString().toFloat()
            val height = binding.edMetricUnitHeight.text.toString().toFloat()

            val bmiResult = calculateBMI(weight, height)
            val screenResult = bmiWeightRanges(bmiResult)
            binding.tvBmi.text = screenResult

        }

    }

    override fun onBackPressed() {
        val exitDialogFragment = ExitDialogFragment()
        exitDialogFragment.show(supportFragmentManager, "MyDialog")
    }

    private fun calculateBMI(weight: Float, height: Float): Float = weight / ((height / 100).pow(2))

    private fun bmiWeightRanges(bmiResult: Float) = when {
        bmiResult < 18.5F -> "YOUR BMI\n$bmiResult\nUnderweight"
        bmiResult in 18.5F..24.9F -> "YOUR BMI\n$bmiResult\nHealthy"
        bmiResult in 25F..29.9F -> "YOUR BMI\n$bmiResult\noverweight"
        bmiResult > 30F -> "YOUR BMI\n$bmiResult\nobese"
        else -> "Error"
    }

}