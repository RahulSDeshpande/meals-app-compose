package com.mojolabs.mealsappcompose.ui.meals.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

@Composable
fun MealCategoryDetailsScreen(mealCategory: MealCategory?) {
    val enumMealImageState = remember { mutableStateOf(EnumMealImageState.NORMAL) }

    val transitionState =
        updateTransition(
            targetState = enumMealImageState,
            label = ""
        )

    val imageSizeDpState =
        transitionState.animateDp(
            targetValueByState = { it.value.size },
            label = ""
        )

    val colorState =
        transitionState.animateColor(
            targetValueByState = { it.value.color },
            label = ""
        )

    val borderState =
        transitionState.animateDp(
            targetValueByState = { it.value.borderWidth },
            label = ""
        )

    Column {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = borderState.value,
                color = colorState.value
            )
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = mealCategory?.imageUrl),
                contentDescription = "Meal image",
                modifier = Modifier.size(imageSizeDpState.value)
            )
        }
        Text(
            text = mealCategory?.name ?: "N/A",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                enumMealImageState.value = enumMealImageState.value.reverse()
            },
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

enum class EnumMealImageState(
    val color: Color,
    val size: Dp,
    val borderWidth: Dp
) {
    NORMAL(
        color = Color.Gray,
        size = 120.dp,
        borderWidth = 8.dp
    ),
    EXPANDED(
        color = Color.Green,
        size = 200.dp,
        borderWidth = 24.dp
    );

    fun reverse() = if (this == NORMAL) EXPANDED else NORMAL
}
