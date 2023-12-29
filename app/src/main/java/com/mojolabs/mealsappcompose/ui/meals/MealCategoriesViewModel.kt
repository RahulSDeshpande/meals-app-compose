package com.mojolabs.mealsappcompose.ui.meals

import androidx.lifecycle.ViewModel
import com.mojolabs.mealsappcompose.model.MealCategoriesResponse
import com.mojolabs.mealsappcompose.repo.MealsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealCategoriesViewModel(
    private val mealsRepository: MealsRepository = MealsRepository()
) : ViewModel() {

    fun getMealCategories(
        onSuccess: (response: MealCategoriesResponse?) -> Unit,
        onFailure: () -> Unit
    ) = mealsRepository
        .getMealCategories()
        .enqueue(
            object : Callback<MealCategoriesResponse> {
                override fun onResponse(
                    call: Call<MealCategoriesResponse>,
                    response: Response<MealCategoriesResponse>
                ) {
                    onSuccess(response.body())
                }

                override fun onFailure(call: Call<MealCategoriesResponse>, t: Throwable) {
                    onFailure()
                }
            }
        )
}
