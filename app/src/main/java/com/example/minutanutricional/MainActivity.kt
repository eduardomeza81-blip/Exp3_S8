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
import com.example.minutanutricional.ui.navigation.Route
import com.example.minutanutricional.ui.screens.HomeMenuScreen
import com.example.minutanutricional.ui.screens.HablarScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import com.example.minutanutricional.ui.BuscarDispositivoActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext



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
    const val HOME = "home"
    const val MINUTA = "minuta"
    const val HABLAR = "hablar"
    const val BUSCAR = "buscar"
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.HOME) },
                onGoToRegistro = { navController.navigate(Routes.REGISTRO) },
                onGoToRecuperar = { navController.navigate(Routes.RECUPERAR) }
            )
        }
        composable(Routes.REGISTRO) {
            RegistroScreen(
                onCrearCuenta = { navController.navigate(Routes.HOME) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.RECUPERAR) {
            RecuperarScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.HOME) {
            HomeMenuScreen(
                onEscribir = { navController.navigate(Routes.MINUTA) },
                onHablar = { /* después lo conectamos */ },
                onBuscarDispositivo = { navController.context.startActivity(
                    Intent(navController.context, BuscarDispositivoActivity::class.java)) },
                onLogout = {
                    navController.popBackStack(Routes.LOGIN, inclusive = false)
                }
            )
        }
        composable(Routes.MINUTA) {
            MinutaScreen(
                onCerrarSesion = {
                    navController.popBackStack(Routes.LOGIN, inclusive = false)
                }
            )
        }
        composable(Routes.HABLAR) {
            HablarScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.BUSCAR) {
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                context.startActivity(Intent(context, BuscarDispositivoActivity::class.java))
                navController.popBackStack() // vuelve al Home al salir de la Activity
            }
        }
        composable("buscar_activity") {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                context.startActivity(
                    Intent(context, BuscarDispositivoActivity::class.java)
                )
                navController.popBackStack()
            }
        }
    }
}
