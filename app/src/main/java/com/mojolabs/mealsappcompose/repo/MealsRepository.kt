package com.mojolabs.mealsappcompose.repo

import com.mojolabs.mealsappcompose.remote.Api

class MealsRepository(private val api: Api = Api()) {

    suspend fun getMealCategories() = api.getMealCategories()
}
