package com.mojolabs.mealsappcompose.ui.meals.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.repo.MealsRepository

class MealCategoryDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mealsRepository: MealsRepository = MealsRepository.instance()

    var mealCategoryState = mutableStateOf<MealCategory?>(null)

    init {
        val meanCategoryId = savedStateHandle["meal_category_id"] ?: ""
        mealCategoryState.value = mealsRepository.getMealCategory(meanCategoryId)
    }
}
