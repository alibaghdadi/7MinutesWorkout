package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityBmiCalculatorBinding
import kotlin.math.pow

class BmiCalculator : AppCompatActivity() {
    private lateinit var binding: ActivityBmiCalculatorBinding
    private var isMetric: Boolean = true
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

        binding.rgUnits.setOnCheckedChangeListener { _, checkedID ->

            when (checkedID) {
                binding.rbMetricUnit.id -> {
                    isMetric = true
                    binding.tilUnitWeight.hint = "Weight (in kg)"
                    binding.tilUnitHeight.hint = "Height (in cm)"
                }

                binding.rbUsUnit.id -> {
                    isMetric = false
                    binding.tilUnitWeight.hint = "Weight (in lb)"
                    binding.tilUnitHeight.hint = "Height (in in)"
                }
            }
        }

        binding.btnBMI.setOnClickListener {

            if (validateMetricUnits()) {
                val weight = binding.edUnitWeight.text.toString().toFloat()
                val height = binding.edUnitHeight.text.toString().toFloat()

                val bmiResult = if (isMetric)
                    calculateBMIMetricUnits(weight, height)
                else
                    calculateBMIUsUnits(weight, height)

                val roundedBMIResult = Math.round(bmiResult * 100.0) / 100.0.toFloat()
                val screenResult = bmiWeightRanges(roundedBMIResult)

                binding.tvBmi.text = screenResult
            }

        }

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val exitDialogFragment = ExitDialogFragment()
        exitDialogFragment.show(supportFragmentManager, "MyDialog")
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        binding.tilUnitWeight.error = null
        binding.tilUnitHeight.error = null

        if (binding.edUnitWeight.text.toString().isEmpty()) {
            isValid = false
            binding.tilUnitWeight.error = "Your weight is empty"
        } else if (binding.edUnitHeight.text.toString().isEmpty()) {
            isValid = false
            binding.tilUnitHeight.error = "Your height is empty"
        }

        return isValid
    }

    private fun calculateBMIMetricUnits(weight: Float, height: Float) =
        weight / ((height / 100).pow(2))

    private fun calculateBMIUsUnits(weight: Float, height: Float) =
        (weight * 703) / ((height).pow(2))

    private fun bmiWeightRanges(bmiResult: Float) = when {
        bmiResult < 18.5F -> "YOUR BMI\n$bmiResult\nUnderweight\n" +
                "Very severely underweight!! Take care of yourself!! Eat more!"

        bmiResult in 18.5F..24.9F -> "YOUR BMI\n$bmiResult\nHealthy"

        bmiResult in 25F..29.9F -> "YOUR BMI\n$bmiResult\nOverweight\n" +
                "Oops! Take care of yourself!! Workout maybe!!"

        bmiResult > 30F -> "YOUR BMI\n$bmiResult\nObese\n" +
                "Oops! Take care of yourself!! Workout maybe!!"

        else -> "Error"
    }

}