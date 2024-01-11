package com.kirdevelopment.worship47compose.presentation

import android.content.Context
import android.util.Log
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.kirdevelopment.worship47compose.common.Constants.DETAILS
import com.kirdevelopment.worship47compose.common.Constants.HOME
import com.kirdevelopment.worship47compose.common.Constants.LOGIN
import com.kirdevelopment.worship47compose.common.Constants.ONBOARDING
import com.kirdevelopment.worship47compose.common.Constants.ON_BOADING
import com.kirdevelopment.worship47compose.presentation.detail_screen.components.DetailScreen
import com.kirdevelopment.worship47compose.presentation.home_screen.components.HomeScreen
import com.kirdevelopment.worship47compose.presentation.login_screen.components.LoginScreen
import com.kirdevelopment.worship47compose.presentation.onboarding_screen.components.OnboardingScreen

class NavGraphBuilder {
    fun createNavGraph(navController: NavHostController, context: MainActivity): NavGraph {

        return navController.createGraph(startDestination =
            when {
                isOnboardingEnded(context) -> LOGIN
                else -> ONBOARDING
            }

        ) {
            composable(HOME) { HomeScreen(navController) }
            composable(DETAILS) { DetailScreen(navController) }
            composable(ONBOARDING) { OnboardingScreen(navController, context) }
            composable(LOGIN) { LoginScreen(navController) }
        }
    }

    private fun hasKey(context: MainActivity): Boolean {
        return false
    }

    private fun isOnboardingEnded(context: MainActivity): Boolean {
        val sharedPreferences = context.getSharedPreferences(ON_BOADING, Context.MODE_PRIVATE)
        Log.d("Вызвалось", sharedPreferences.getBoolean(ONBOARDING, false).toString())
        return sharedPreferences.getBoolean(ONBOARDING, false)
    }
}