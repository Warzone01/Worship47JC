package com.kirdevelopment.worship47compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kirdevelopment.worship47compose.presentation.ui.components.CustomTopAppBar
import com.kirdevelopment.worship47compose.presentation.ui.theme.Worship47ComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Worship47ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomTopAppBar()
                    val navController = rememberNavController()
                    val navGraph = NavGraphBuilder().createNavGraph(navController, this)
                    NavHost(
                        navController = navController,
                        graph = navGraph
                    )
                }
            }
        }
    }
}
