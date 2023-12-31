package com.mojolabs.mealsappcompose.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

class MainActivity : ComponentActivity() {

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
    Column {
        Text(
            text = "Meal Categories\n--------------------------",
            modifier = modifier
        )

        val mealCategoriesViewModel: MealCategoriesViewModel = viewModel()

        mealCategoriesViewModel.getMealCategories()

        // val mealCategories = mealCategoriesViewModel.mealCategoriesState
        val mealCategories = mealCategoriesViewModel.mealCategoriesEvent.observeAsState().value

        mealCategories?.apply {
            if (mealCategories.mealsCategories.isEmpty().not()) {
                LazyColumn {
                    items(mealCategories.mealsCategories) {
                        Text(
                            text = it.name,
                            modifier = modifier
                        )
                    }
                }
            } else {
                // ERROR
            }
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
