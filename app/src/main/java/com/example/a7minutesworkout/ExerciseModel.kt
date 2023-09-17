package com.example.a7minutesworkout

class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean = false,
    private var isSelected: Boolean = false,
) {
    fun getId() = id
    fun setId(id: Int) {
        this.id = id
    }

    fun getName() = name
    fun setName(name: String) {
        this.name = name
    }

    fun getImage() = image
    fun setImage(image: Int) {
        this.image = image
    }

    fun getIsCompleted() = isCompleted
    fun setIsCompleted(isCompleted: Boolean) {
        this.isCompleted = isCompleted
    }

    fun getIsSelected() = isSelected
    fun setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}