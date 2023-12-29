package com.mojolabs.mealsappcompose.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mojolabs.mealsappcompose.model.MealCategoriesResponse
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

class MainActivity : ComponentActivity() {

    // private val mealCategoriesViewModel: MealsCategoriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealsCategoriesScreen("Android")
                }
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    val mealCategoriesViewModel: MealCategoriesViewModel = viewModel()

    val rememberedMealCategories = remember { mutableStateOf(MealCategoriesResponse(listOf())) }

    mealCategoriesViewModel.getMealCategories(
        onSuccess = { response ->
            if (response != null) {
                rememberedMealCategories.value = response
            }
        },
        onFailure = {
        }
    )

    LazyColumn {
        items(rememberedMealCategories.value.mealsCategories) {
            Text(
                text = it.name,
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoriesScreenPreview() {
    MealsAppComposeTheme {
        MealsCategoriesScreen("Android")
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
