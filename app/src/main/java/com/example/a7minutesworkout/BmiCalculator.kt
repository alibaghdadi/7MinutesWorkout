package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityBmiCalculatorBinding

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
        }

        binding.toolbarBMICalculator.setNavigationOnClickListener {
            val exitDialogFragment = ExitDialogFragment()
            exitDialogFragment.show(supportFragmentManager, "MyDialog")
        }

    }
}