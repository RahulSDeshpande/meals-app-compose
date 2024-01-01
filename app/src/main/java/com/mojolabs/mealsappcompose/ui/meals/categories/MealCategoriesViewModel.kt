package com.mojolabs.mealsappcompose.ui.meals.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mojolabs.mealsappcompose.model.MealCategoriesResponse
import com.mojolabs.mealsappcompose.repo.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealCategoriesViewModel(
    private val mealsRepository: MealsRepository = MealsRepository.instance()
) : ViewModel() {

    // val mealCategoriesState = mutableStateOf(MealCategoriesResponse(listOf()))

    val mealCategoriesEvent = MutableLiveData<MealCategoriesResponse>()

    fun getMealCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            // mealCategoriesState.value = mealsRepository.getMealCategories()
            mealCategoriesEvent.postValue(mealsRepository.getMealCategories())
        }
    }
}
