package com.example.olmedocoffeeshops

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myprojects.R
import com.example.theme.GoldSerifTittle
import com.example.theme.GoldTittle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Coffee(navController: NavHostController) {

    LazyColumn(modifier = Modifier.background(Color.LightGray)) {
        items(getUser()) { item ->
            ItemOption(option = item, navController = navController)
        }
    }


}

@Composable
fun ItemOption(
    option: infoCard,
    navController: NavHostController
) {
    val rating = remember { mutableStateOf(0) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .clickable { navController.navigate("Comentarios/${option.title}") }
    ) {
        Image(
            painter = painterResource(id = option.imageResId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            contentDescription = null,
        )

        Text(
            text = option.title,
            fontSize = 30.sp,
            fontFamily = GoldTittle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp),
            color = Color.Black
        )

        InteractiveStarRatingBar(rating)

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = option.lugar,
            fontSize = 16.sp,
            fontFamily = GoldSerifTittle,
            modifier = Modifier.padding(4.dp),
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(4.dp))


        Divider()

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
            Text(text = "Reserve")
        }
    }
}

@Composable
fun InteractiveStar(
    isFilled: Boolean,
    onStarClick: () -> Unit
) {
    val starOutlinePainter = painterResource(id = R.drawable.staroutline)
    val Star = painterResource(id = R.drawable.star)


    Icon(
        painter = if (isFilled) Star else starOutlinePainter,
        contentDescription = null,
        tint = if (isFilled) Color.Gray else Color.Black,
        modifier = Modifier.clickable(onClick = onStarClick)
    )

}

@Composable
fun InteractiveStarRatingBar(
    rating: MutableState<Int>,
    maxStars: Int = 5
) {
    Row(
        modifier = Modifier.padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 1..maxStars) {
            InteractiveStar(
                isFilled = i <= rating.value,
                onStarClick = {
                    rating.value = i
                }
            )
        }
    }
}

fun getUser(): List<infoCard> {
    return listOf(
        infoCard("Antonio Caffè Greco", "St. Italy, Rome", R.drawable.images),
        infoCard("Coffee Room", "St. Germany, Berlin", R.drawable.images1),
        infoCard("Coffee Ibiza", "St. Colon, Madrid", R.drawable.images2),
        infoCard("Pudding Coffe Shop", "St. Diagonal, Barceona", R.drawable.images3),
        infoCard("L`Express", "St. Picadilly Circus, London", R.drawable.images4),
        infoCard("Coffee Corner", "St.Àngel Guimerà, Valencia", R.drawable.images5),
        infoCard("Sweet Cup", "St. Kinkerstraat, Amsterdam", R.drawable.images6),
    )
}