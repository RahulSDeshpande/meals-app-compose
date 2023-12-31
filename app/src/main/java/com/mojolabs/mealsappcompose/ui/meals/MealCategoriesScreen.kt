package com.mojolabs.mealsappcompose.ui.meals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

@Composable
fun MealCategoriesScreen(modifier: Modifier = Modifier) {
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
        MealCategoriesScreen()
    }
}
