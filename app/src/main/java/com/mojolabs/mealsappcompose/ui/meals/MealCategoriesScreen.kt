package com.mojolabs.mealsappcompose.ui.meals

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.mojolabs.mealsappcompose.model.MealCategory
import com.mojolabs.mealsappcompose.ui.theme.MealsAppComposeTheme

@Composable
fun MealCategoriesScreen(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Meal Categories",
            modifier = modifier.padding(16.dp)
        )

        val mealCategoriesViewModel: MealCategoriesViewModel = viewModel()

        mealCategoriesViewModel.getMealCategories()

        // val mealCategories = mealCategoriesViewModel.mealCategoriesState
        val mealCategories = mealCategoriesViewModel.mealCategoriesEvent.observeAsState().value

        mealCategories?.apply {
            if (mealCategories.mealsCategories.isEmpty().not()) {
                LazyColumn {
                    items(mealCategories.mealsCategories) { mealCategory ->
                        MealCategories(mealCategory)
                    }
                }
            } else {
                // ERROR
            }
        }
    }
}

@Composable
fun MealCategories(mealCategory: MealCategory) {
    val isExpanded = remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row {
            Image(
                // painter = painterResource(id = photoResId),
                painter = rememberAsyncImagePainter(model = mealCategory.imageUrl),
                contentDescription = "Meal category image",
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Inside
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(4.dp)
            ) {
                Text(
                    text = mealCategory.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = mealCategory.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alpha(0.7f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded.value) 10 else 2
                )
            }

            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 16.dp)
                    .align(Alignment.Top)
                    .clickable { isExpanded.value = isExpanded.value.not() }
            )
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
