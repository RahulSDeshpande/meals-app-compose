package com.mojolabs.mealsappcompose.ui.meals

import androidx.lifecycle.ViewModel
import com.mojolabs.mealsappcompose.repo.MealsRepository

class MealCategoriesViewModel(
    private val mealsRepository: MealsRepository = MealsRepository()
) : ViewModel() {

    // val mealCategories = MutableLiveData<MealCategoriesResponse>()

    suspend fun getMealCategories() = mealsRepository.getMealCategories()
}
