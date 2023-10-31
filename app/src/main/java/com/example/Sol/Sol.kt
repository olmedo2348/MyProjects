package com.example.olmedoproyectosol

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myprojects.R

@Composable
fun Sol() {
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(getUser()) { item ->
            ItemOption(option = item)
        }
    })
}

@Composable
fun ItemOption(
    option: infoCard,
) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Image(
            painter = painterResource(id = option.imageResId),
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    Toast
                        .makeText(context, option.titulo, Toast.LENGTH_LONG)
                        .show()
                },
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        BottomAppBar(
            modifier = Modifier.height(50.dp),
            actions = {
                Text(text = option.titulo, modifier = Modifier.padding(10.dp, bottom = 3.dp))
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    Modifier.width(150.dp)
                ) {
                    DropdownMenuItem(text = { Text(text = "Compartir") },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Compartir"
                            )
                        })
                    DropdownMenuItem(text = { Text(text = "Lock") },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Compartir"
                            )
                        })
                }
            }
        )
    }
}

fun getUser(): List<infoCard> {
    return listOf(
        infoCard("Corona Solar", R.drawable.corona_solar),
        infoCard("Erupcion Solar", R.drawable.erupcionsolar),
        infoCard("Espiculas", R.drawable.espiculas),
        infoCard("Filamentos", R.drawable.filamentos),
        infoCard("Magnetosfera", R.drawable.magnetosfera),
        infoCard("Mancha Solar", R.drawable.manchasolar),
    )
}