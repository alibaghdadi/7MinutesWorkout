package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityBmiCalculatorBinding
import kotlin.math.pow
import java.text.DecimalFormat

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

            if (validateMetricUnits()) {
                val weight = binding.edMetricUnitWeight.text.toString().toFloat()
                val height = binding.edMetricUnitHeight.text.toString().toFloat()

                val bmiResult = calculateBMI(weight, height)
                val roundedBMIResult = Math.round(bmiResult * 100.0) / 100.0.toFloat()
                val screenResult = bmiWeightRanges(roundedBMIResult)

                binding.tvBmi.text = screenResult
            }

        }

    }

    override fun onBackPressed() {
        val exitDialogFragment = ExitDialogFragment()
        exitDialogFragment.show(supportFragmentManager, "MyDialog")
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        binding.tilMetricUnitWeight.error = null
        binding.tilMetricUnitHeight.error = null

        if (binding.edMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
            binding.tilMetricUnitWeight.error = "Your weight is empty"
        } else if (binding.edMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
            binding.tilMetricUnitHeight.error = "Your height is empty"
        }

        return isValid
    }

    private fun calculateBMI(weight: Float, height: Float): Float =
        weight / ((height / 100).pow(2))

    private fun bmiWeightRanges(bmiResult: Float) = when {
        bmiResult < 18.5F -> "YOUR BMI\n$bmiResult\nUnderweight\n" +
                "Severely underweight!! Take care of yourself!! Eat more!"

        bmiResult in 18.5F..24.9F -> "YOUR BMI\n$bmiResult\nHealthy"

        bmiResult in 25F..29.9F -> "YOUR BMI\n$bmiResult\nOverweight\n" +
                "Oops! Take care of yourself!! Workout maybe!!"

        bmiResult > 30F -> "YOUR BMI\n$bmiResult\nObese\n" +
                "Oops! Take care of yourself!! Workout maybe!!"

        else -> "Error"
    }

}