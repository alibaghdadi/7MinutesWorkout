package com.example.a7minutesworkout

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ExitDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Exit")
            .setMessage("Are you sure to Exit!")
            .setPositiveButton("OK") { _, _ ->
                ExerciseActivity().onBackPressed()
            }
            .setNegativeButton("Cancel") { _, _ ->
            }
        return builder.create()
    }
}