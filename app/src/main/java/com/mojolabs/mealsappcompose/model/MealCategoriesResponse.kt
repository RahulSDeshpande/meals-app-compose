package com.mojolabs.mealsappcompose.model

import com.google.gson.annotations.SerializedName

data class MealCategoriesResponse(
    @SerializedName("categories")
    val mealsCategories: List<MealCategory>
)

data class MealCategory(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryDescription")
    val description: String,
    @SerializedName("strCategoryThumb")
    val imageUrl: String
)
