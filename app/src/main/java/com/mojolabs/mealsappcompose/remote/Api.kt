package com.mojolabs.mealsappcompose.remote

import com.mojolabs.mealsappcompose.model.MealCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class Api {

    private var apiService: ApiService

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getMealCategories() = apiService.getMealCategories()
}

interface ApiService {

    @GET("v1/1/categories.php")
    fun getMealCategories(): Call<MealCategoriesResponse>
}
