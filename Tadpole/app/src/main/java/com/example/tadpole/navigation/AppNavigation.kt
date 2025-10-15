package com.example.tadpole.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tadpole.ui.theme.home.HomeScreen
import com.example.tadpole.ui.theme.login.LoginScreen
import com.example.tadpole.viewmodel.UserViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun AppNavigation(navController: NavHostController, userViewModel: UserViewModel) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Home.route) },
                userViewModel = userViewModel
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                userViewModel = userViewModel,
                onLogout = {
                    userViewModel.logout()
                    navController.navigate(Screen.Login.route)
                }
            )
        }
    }
}