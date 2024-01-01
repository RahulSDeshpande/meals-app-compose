package com.mojolabs.mealsappcompose.ui.meals.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberAsyncImagePainter
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme
import kotlin.math.min

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

    val scrollState = rememberScrollState()

    val offset = min(1f, 1 - (scrollState.value / 600f))

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Surface(shadowElevation = 4.dp) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = borderState.value,
                            color = colorState.value
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = mealCategory?.imageUrl),
                            contentDescription = "Meal image",
                            // modifier = Modifier.size(imageSizeDpState.value)
                            modifier = Modifier.size(max(100.dp, 200.dp * offset))
                        )
                    }
                    Text(
                        text = mealCategory?.name ?: "N/A",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ).align(Alignment.CenterHorizontally)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
            ) {
                Text(
                    text = mealCategory?.description?.repeat(10) ?: "N/A",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        enumMealImageState.value = enumMealImageState.value.reverse()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Change state of meal category photo")
                }
            }
        }
    }
}

@Preview(
    showBackground = true
    // showSystemUi = true
)
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
        color = Color.DarkGray,
        size = 200.dp,
        borderWidth = 12.dp
    );

    fun reverse() = if (this == NORMAL) EXPANDED else NORMAL
}
