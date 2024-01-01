package com.mojolabs.mealsappcompose.ui.meals.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

@Composable
fun MealCategoryDetailsScreen(mealCategory: MealCategory?) {
    val isExpanded = remember { mutableStateOf(false) }
    val imageSize =
        animateDpAsState(
            targetValue = if (isExpanded.value) 200.dp else 100.dp,
            label = ""
        )

    Column {
        Card(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = mealCategory?.imageUrl),
                contentDescription = "Meal image",
                modifier = Modifier.size(imageSize.value)
            )
        }
        Text(
            text = mealCategory?.name ?: "N/A",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { isExpanded.value = isExpanded.value.not() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Change state of meal profile picture")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoriesScreenPreview() {
    MealsAppComposeTheme {
        MealCategoryDetailsScreen(mealCategory = null)
    }
}
