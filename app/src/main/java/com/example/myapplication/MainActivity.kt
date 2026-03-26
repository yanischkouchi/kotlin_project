package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.myapplication.data.AppDb
import com.example.myapplication.ui.*
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "shopping-db"
        ).build()
        val dao = db.taskDao()

        val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory() }
        val searchViewModel: SearchViewModel by viewModels { SearchViewModelFactory(dao) }
        val profileViewModel: ProfileViewModel by viewModels { ProfileViewModelFactory() }

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var currentScreen by remember { mutableStateOf("home") }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                label = { Text("Home") },
                                selected = currentScreen == "home",
                                onClick = { currentScreen = "home" }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                                label = { Text("Search") },
                                selected = currentScreen == "search",
                                onClick = { currentScreen = "search" }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                                label = { Text("Profile") },
                                selected = currentScreen == "profile",
                                onClick = { currentScreen = "profile" }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            "home" -> HomeScreen(homeViewModel)
                            "search" -> SearchScreen(searchViewModel)
                            "profile" -> ProfileScreen(profileViewModel)
                        }
                    }
                }
            }
        }
    }
}
