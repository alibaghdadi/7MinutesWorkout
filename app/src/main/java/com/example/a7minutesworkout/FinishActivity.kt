package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarFinish)

        // To add the back button in the toolbar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarFinish.setNavigationOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnFinish.setOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}