package com.mojolabs.mealsappcompose.repo

import com.mojolabs.mealsappcompose.remote.Api

class MealsRepository(private val api: Api = Api()) {

    fun getMealCategories() = api.getMealCategories()
}
