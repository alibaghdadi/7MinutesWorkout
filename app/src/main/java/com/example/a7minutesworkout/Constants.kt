package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {

        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "jumping_jacks",
            R.drawable.ic_jumping_jacks,
            isCompleted = false,
            isSelected = false
        )

        val wallSit = ExerciseModel(
            2,
            "wall_sit",
            R.drawable.ic_wall_sit
        )

        val pushUp = ExerciseModel(
            3,
            "push_up",
            R.drawable.ic_push_up
        )

        val abdominalCrunch = ExerciseModel(
            4,
            "abdominal_crunch",
            R.drawable.ic_abdominal_crunch
        )

        val stepUpOntoChair = ExerciseModel(
            5,
            "step_up_onto_chair",
            R.drawable.ic_step_up_onto_chair
        )

        val squat = ExerciseModel(
            6,
            "squat",
            R.drawable.ic_squat
        )

        val tricepsDipOnChair = ExerciseModel(
            7,
            "triceps_dip_on_chair",
            R.drawable.ic_triceps_dip_on_chair
        )

        val plank = ExerciseModel(
            8,
            "plank",
            R.drawable.ic_plank
        )

        val highKneesRunningInPlace = ExerciseModel(
            9,
            "high_knees_running_in_place",
            R.drawable.ic_high_knees_running_in_place
        )

        val lunge = ExerciseModel(
            10,
            "lunge",
            R.drawable.ic_lunge
        )

        val pushUpAndRotation = ExerciseModel(
            11,
            "push_up_and_rotation",
            R.drawable.ic_push_up_and_rotation
        )

        val sidePlank = ExerciseModel(
            12,
            "side_plank",
            R.drawable.ic_side_plank
        )

        exerciseList.add(jumpingJacks)
        exerciseList.add(wallSit)
        exerciseList.add(pushUp)
        exerciseList.add(abdominalCrunch)
        exerciseList.add(stepUpOntoChair)
        exerciseList.add(squat)
        exerciseList.add(tricepsDipOnChair)
        exerciseList.add(plank)
        exerciseList.add(highKneesRunningInPlace)
        exerciseList.add(lunge)
        exerciseList.add(pushUpAndRotation)
        exerciseList.add(sidePlank)

        return exerciseList
    }
}