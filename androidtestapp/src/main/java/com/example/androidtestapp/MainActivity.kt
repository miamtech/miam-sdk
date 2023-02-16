package com.example.androidtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidtestapp.models.Route
import com.example.androidtestapp.views.Basket
import com.example.androidtestapp.views.DeepLinkDropDownMenu
import com.example.androidtestapp.views.Home
import com.miam.core.localisation.I18nResolver
import com.miam.kmmMiamCore.services.RouteServiceInstance
import com.miam.kmm_miam_sdk.android.ui.components.catalog.Catalog
import com.miam.kmm_miam_sdk.android.ui.components.favoritePage.FavoritePage
import com.miam.kmm_miam_sdk.android.ui.components.myMeal.MyMeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity: ComponentActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val routes = listOf(
        Route("home", "home", Icons.Filled.Home),
        Route("Favorite", "favorite", Icons.Filled.Favorite),
        Route("Catalogue", "catalogCategories", Icons.Filled.Book),
        Route("Mes Repas", "myMeal", Icons.Filled.FoodBank)
    )

    private fun initMiam() {
        MiamManager()
        I18nResolver.registerContext(this.applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMiam()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Column {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Miam  demo") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    val previousRoute = RouteServiceInstance.instance.previous()
                                    if (previousRoute == null && navController.backQueue.isNotEmpty()) {
                                        (navController.backQueue[0].destination as NavGraph).startDestinationRoute?.let { it1 ->
                                            navController.navigate(
                                                it1
                                            )
                                        }
                                    }
                                }) {
                                    Icon(Icons.Filled.ArrowBack, null)
                                }
                            }, actions = {
                                IconButton(onClick = {
                                    navController.navigate("basket") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }) {
                                    Icon(Icons.Filled.ShoppingCart, null)
                                }
                                DeepLinkDropDownMenu {
                                    navController.navigate("catalog/$it") {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }.Content()
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigation {
                            routes.forEach { route ->
                                BottomNavigationItem(
                                    icon = { Icon(route.icon, contentDescription = null) },
                                    label = { Text(route.label) },
                                    selected = currentDestination?.hierarchy?.any { it.route == route.value } == true,
                                    onClick = {
                                        navController.navigate(route.value) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { padding ->
                    NavHost(modifier = Modifier.padding(padding), navController = navController, startDestination = "home") {
                        composable("home") {
                            Box {
                                Home(this@MainActivity).Content()
                            }
                        }
                        composable("myMeal") {
                            Column(Modifier.verticalScroll(rememberScrollState())) {
                                MyMeal(this@MainActivity).Content()
                            }
                        }
                        composable("favorite") { FavoritePage(this@MainActivity).Content() }
                        composable("catalog/{Id}") {
                            Catalog(this@MainActivity).apply { bind(it.arguments?.getString("Id") ?: "", "") }.Content()
                            BackHandler {
                                val previousRoute = RouteServiceInstance.instance.previous()
                                if (previousRoute == null && navController.backQueue.isNotEmpty()) {
                                    (navController.backQueue[0].destination as NavGraph).startDestinationRoute?.let { it1 ->
                                        navController.navigate(
                                            it1
                                        )
                                    }
                                }
                            }
                        }
                        composable("catalogCategories") {
                            Catalog(this@MainActivity).apply { enablePreference(true) }.Content()
                            BackHandler {
                                val previousRoute = RouteServiceInstance.instance.previous()
                                if (previousRoute == null && navController.backQueue.isNotEmpty()) {
                                    (navController.backQueue[0].destination as NavGraph).startDestinationRoute?.let { it1 ->
                                        navController.navigate(
                                            it1
                                        )
                                    }
                                }
                            }
                        }
                        composable("basket") {
                            Box {
                                Basket().Content()
                            }
                        }
                    }
                }
            }
        }
    }
}

