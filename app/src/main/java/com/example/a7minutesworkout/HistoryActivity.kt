package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarHistory)

        // To add the back button in the toolbar
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarHistory.setNavigationOnClickListener {
            val exitDialogFragment = ExitDialogFragment()
            exitDialogFragment.show(supportFragmentManager, "MyDialog")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val exitDialogFragment = ExitDialogFragment()
        exitDialogFragment.show(supportFragmentManager, "MyDialog")
    }
}