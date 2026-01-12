package com.example.minutanutricional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minutanutricional.ui.screens.LoginScreen
import com.example.minutanutricional.ui.screens.MinutaScreen
import com.example.minutanutricional.ui.screens.RecuperarScreen
import com.example.minutanutricional.ui.screens.RegistroScreen
import com.example.minutanutricional.ui.theme.MinutaNutricionalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinutaNutricionalTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

private object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val RECUPERAR = "recuperar"
    const val MINUTA = "minuta"
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.MINUTA) },
                onGoToRegistro = { navController.navigate(Routes.REGISTRO) },
                onGoToRecuperar = { navController.navigate(Routes.RECUPERAR) }
            )
        }
        composable(Routes.REGISTRO) {
            RegistroScreen(
                onCrearCuenta = { navController.navigate(Routes.MINUTA) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.RECUPERAR) {
            RecuperarScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.MINUTA) {
            MinutaScreen(
                onCerrarSesion = {
                    navController.popBackStack(Routes.LOGIN, inclusive = false)
                }
            )
        }
    }
}
