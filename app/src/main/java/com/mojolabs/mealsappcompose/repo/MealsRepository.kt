package com.mojolabs.mealsappcompose.repo

import com.mojolabs.mealsappcompose.model.MealCategoriesResponse
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.remote.Api

class MealsRepository(private val api: Api = Api()) {

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun instance() =
            instance ?: synchronized(this) {
                instance ?: MealsRepository().also { instance = it }
            }
    }

    private var cachedMeals = listOf<MealCategory>()

    suspend fun getMealCategories(): MealCategoriesResponse {
        val mealCategories = api.getMealCategories()
        cachedMeals = mealCategories.mealsCategories
        return mealCategories
    }

    fun getMealCategory(mealCategoryId: String) =
        cachedMeals.firstOrNull { it.id == mealCategoryId }
}
