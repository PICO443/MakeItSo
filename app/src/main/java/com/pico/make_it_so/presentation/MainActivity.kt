package com.pico.make_it_so.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pico.make_it_so.presentation.add_edit_task.AddEditScreen
import com.pico.make_it_so.presentation.home.HomeScreen
import com.pico.make_it_so.presentation.log_in.LoginScreen
import com.pico.make_it_so.presentation.settings.SettingsScreen
import com.pico.make_it_so.presentation.sign_up.SignUpScreen
import com.pico.make_it_so.presentation.splash.SplashScreen
import com.pico.make_it_so.presentation.splash.SplashViewModel
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeItSoTheme {
                val navController = rememberNavController()
                val viewModel: SplashViewModel = hiltViewModel()
                NavHost(
                    navController = navController,
                    startDestination = if (viewModel.uiState.isLoggedIn) Screen.Home.route else Screen.Splash.route
                ) {
                    composable(Screen.SignUpScreen.route) {
                        SignUpScreen(
                            navigateBack = { navController.navigateUp() },
                            navigateToHome = { navController.navigate(Screen.Home.route) })
                    }
                    composable(Screen.SettingsScreen.route) {
                        SettingsScreen(navigateBack = { navController.navigateUp() })
                    }
                    composable(Screen.LoginScreen.route) {
                        LoginScreen(
                            navigateBack = { navController.navigateUp() },
                            navigateToHome = { navController.navigate(Screen.Home.route) })
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(
                            navigateToLogin = { navController.navigate(Screen.LoginScreen.route) },
                            navigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) },
                            navigateToAddEditScreen = { navController.navigate(Screen.AddEditScreen.route) }
                        )
                    }
                    composable(Screen.Splash.route) {
                        SplashScreen(
                            navigateToHome = { navController.navigate(Screen.Home.route) },
                            navigateToSignUp = { navController.navigate(Screen.SignUpScreen.route) })
                    }
                    composable(Screen.AddEditScreen.route) {
                        AddEditScreen(navigateToHome = { navController.navigate(Screen.Home.route) }, navigateBack = { navController.navigateUp()})
                    }
                }
            }
        }
    }
}