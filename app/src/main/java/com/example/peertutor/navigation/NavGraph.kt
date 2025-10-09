package com.example.peertutor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.peertutor.ui.splash.SplashScreen
import com.example.peertutor.ui.signup.SignUpScreen
import com.example.peertutor.ui.signup.LoginScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object SignUp : Screen("signup")
    object Login : Screen("login")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onGetStartedClick = { navController.navigate(Screen.SignUp.route) },
                onLoginClick = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onCancelClick = { navController.popBackStack() },
                onLoginClick = { navController.navigate(Screen.Login.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onCancelClick = { navController.popBackStack() },
                onLoginClick = { navController.navigate(Screen.SignUp.route) }
            )
        }
    }
}
