package com.example

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myprojects.R
import com.example.theme.MyProjectsTheme
import com.example.olmedocoffeeshops.Coffee
import com.example.olmedocoffeeshops.Comentarios
import com.example.olmedomyphoto.Picture
import com.example.olmedoproyectosol.Sol
import com.example.theme.Pink80
import com.example.theme.PurpleGrey80
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyProjectsTheme {
                Scaffold(
                    bottomBar = { MyNavegation(navController) }
                ) {
                    NavHost(navController = navController, startDestination = "Portada")
                    {
                        composable("Portada"){
                            Portada(navController = navController)
                        }
                        composable("MyPhotos"){
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                Picture()
                            }
                        }
                        composable("CoffeShops"){
                            Scaffold(
                                topBar = { MyTopBar() }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = it.calculateTopPadding())
                                        .background(PurpleGrey80)
                                ) {
                                    val navController = rememberNavController()
                                    NavHost(navController = navController, startDestination = "Coffee") {
                                        composable("Coffee") { Coffee(navController = navController) }
                                        composable(
                                            route = "Comentarios/{cafeteriaName}",
                                            arguments = listOf(navArgument("cafeteriaName") { type = NavType.StringType })
                                        ) { backStackEntry ->
                                            Comentarios(backStackEntry.arguments?.getString("cafeteriaName") ?: "", navController)
                                        }
                                    }
                                }
                            }
                        }
                        composable("ElSol"){
                            val drawerState = rememberDrawerState(DrawerValue.Closed)
                            // A surface container using the 'background' color from the theme
                            Scaffold(
                                bottomBar = { MyBottomBar(drawerState) },
                                content = {
                                    ModalNavigationDrawer(
                                        drawerState = drawerState,
                                        drawerContent = {
                                            ModalDrawerSheet {
                                                Image(
                                                    painter = painterResource(id = R.drawable.sol),
                                                    contentDescription = null,
                                                    modifier = Modifier.fillMaxWidth()
                                                )

                                                Spacer(modifier = Modifier.height(16.dp))
                                                NavigationDrawerItem(
                                                    icon = {
                                                        Icon(
                                                            Icons.Filled.Build,
                                                            contentDescription = null
                                                        )
                                                    },
                                                    label = { Text(text = "Build") },
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                )
                                                NavigationDrawerItem(
                                                    icon = {
                                                        Icon(
                                                            Icons.Filled.Info,
                                                            contentDescription = null
                                                        )
                                                    },
                                                    label = { Text(text = "Info") },
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                )
                                                NavigationDrawerItem(
                                                    icon = {
                                                        Icon(
                                                            Icons.Filled.Email,
                                                            contentDescription = null
                                                        )
                                                    },
                                                    label = { Text(text = "Email") },
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                )
                                            }
                                        },
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(bottom = it.calculateBottomPadding())
                                                .background(PurpleGrey80)
                                        ) {
                                            Sol()
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomBar(drawerState: DrawerState) {
    var like by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    BottomAppBar(
        containerColor = Color.Red,
        actions = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Navegation Back",
                    tint = Color.White
                )
            }
            BadgedBox(
                badge = {
                    Badge { Text(text = like.toString()) }
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Likes",
                    tint = Color.White,
                    modifier = Modifier.clickable { like = like + 1 }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = FloatingActionButtonDefaults.containerColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    )
}


@Composable
fun MyNavegation(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            },
            selected = true,
            onClick = {navController.navigate("MyPhotos")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            },
            selected = false,
            onClick = { navController.navigate("CoffeShops") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            },
            selected = false,
            onClick = { navController.navigate("ElSol") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "CoffeShop") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Pink80),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
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
                    leadingIcon = { Icon(imageVector = Icons.Filled.Share, contentDescription = "Compartir") })
                DropdownMenuItem(text = { Text(text = "Lock") },
                    onClick = { /*TODO*/ },
                    leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Compartir") })
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProjectsTheme {
    }
}