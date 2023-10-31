package com.example.olmedocoffeeshops

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.theme.GoldTittle

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Comentarios(navControllerName: String, navController: NavHostController) {
    val rvState = rememberLazyStaggeredGridState()
    val showButton by remember { derivedStateOf { rvState.firstVisibleItemIndex > 0}}

    Column(modifier = Modifier.background(Color.LightGray)) {
        Text(
            text = navControllerName,
            fontSize = 35.sp,
            fontFamily = GoldTittle,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp),
            color = Color.Black
        )
        LazyVerticalStaggeredGrid(
            state = rvState,
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.weight(1f)
        ) {
            items(getComentario().size) { coment ->
                ItemsComentario(comentario = coment)
            }
        }

        if (showButton) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Add new comment")
            }
        }
    }

}


fun getComentario(): List<String> {
    return listOf(
        "Servicio algo flojo, aún así lo recomiendo",
        "Céntrica y acogedora. Volveremos seguro",
        "La ambientacion muy buena, pero en la planta de arriba un poco escasa.",
        "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos.\n",
        "El personal muy amable, nos permitieron ver todo el establecimiento.\n",
        "Muy bueno",
        "Excelente. Destacable la extensa carta de cafés",
        "Buen ambiente y buen servicio. Lo recomiendo.",
        "En días festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré",
        "Repetiremos. Gran selección de tartas y cafés.",
        "Todo lo que he probado en la cafetería está riquísimo, dulce o salado.\n",
        "La vajilla muy bonita todo de diseño que en el entorno del bar queda ideal.\n",
        "Puntos negativos: el servicio es muy lento y los precios son un poco elevados.",
    )
}

@Composable
fun ItemsComentario(comentario: Int) {
    val coment = getComentario()
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { }
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = coment[comentario], fontSize = 16.sp)
            }
        }
    }

}