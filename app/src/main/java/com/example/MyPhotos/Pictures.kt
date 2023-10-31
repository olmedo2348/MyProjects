package com.example.olmedomyphoto

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myprojects.R

@Composable
fun Picture() {
    var selecItem: Int? by remember { mutableStateOf(null) }
    LazyRow() {
        items(getPhotos()){ itemImage ->
            PhotoItem(itemImage = itemImage) { selecItem = it }
        }
    }
    selecItem?.let { selecItem ->
        Image(
            painter = painterResource(id = selecItem),
            contentDescription = "",
            modifier = Modifier.padding(all = 5.dp),
            contentScale = ContentScale.Fit
        )
    }

}

@Composable
fun PhotoItem(itemImage: Int, onItemSelected: (Int) -> Unit) {
    Box() {
        Image(
            painter = painterResource(id = itemImage),
            modifier = Modifier
                .padding(10.dp)
                .clickable { onItemSelected(itemImage) },
            contentDescription = "",
            contentScale = ContentScale.Inside
        )
    }
}

fun getPhotos(): List<Int> {
    return listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
    )
}
