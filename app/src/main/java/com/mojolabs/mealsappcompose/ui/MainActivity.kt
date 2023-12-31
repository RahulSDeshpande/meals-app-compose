package com.mojolabs.mealsappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mojolabs.mealsappcompose.ui.meals.MealCategoriesScreen
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
                    MealCategoriesScreen()
                }
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
