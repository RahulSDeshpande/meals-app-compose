package com.mojolabs.mealsappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mojolabs.mealsappcompose.ui.meals.categories.MealCategoriesScreen
import com.mojolabs.mealsappcompose.ui.meals.details.MealCategoryDetailsScreen
import com.mojolabs.mealsappcompose.ui.meals.details.MealCategoryDetailsViewModel
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppComposeTheme {
                MealsAppNavigator()
            }
        }
    }
}

@Composable
fun MealsAppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "meal_categories"
    ) {
        composable(route = "meal_categories") {
            MealCategoriesScreen(
                navCallback = { mealCategoryId ->
                    navController.navigate(route = "meal_category_details/$mealCategoryId")
                }
            )
        }
        composable(
            route = "meal_category_details/{meal_category_id}",
            arguments = listOf(
                navArgument("meal_category_id") {
                    type = NavType.StringType
                }
            )
        ) {
            val mealCategoryDetailsViewModel: MealCategoryDetailsViewModel = viewModel()
            mealCategoryDetailsViewModel.mealCategoryState.value?.let { mealCategory ->
                MealCategoryDetailsScreen(
                    // mealCategoryId = it.arguments?.getString("meal_category_id"),
                    mealCategory = mealCategory
                    // navController = navController
                )
            }
        }
    }
}

fun main() {
    val string = "1+2-3+4"
    println(evaluate(string)) // 10

    // val string2 = "12+41"
    // println(evaluate(string2)) // 53
}

fun evaluate(str: String): Int {
    val numbers = str.split("-")
    println(numbers)
    var sum = 0
    numbers.forEach { value ->
        if (sum == 0 && value.toIntOrNull() != null) {
            sum = value.toInt()
        } else {
            sum -= evaluatePlus(value)
        }
    }

    return sum
}

fun evaluatePlus(str: String): Int {
    val numbers = str.split("+")
    println(numbers)
    var sum = 0
    numbers.forEach { value ->
        sum += value.toInt()
    }

    return sum
}
