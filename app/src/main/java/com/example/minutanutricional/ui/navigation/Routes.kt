package com.example.minutanutricional.ui.navigation

sealed class Route(val path: String) {
    data object Login : Route("login")
    data object Register : Route("registro")
    data object Recover : Route("recuperar")
    data object Home : Route("home")
    data object Escribir : Route("escribir")
    data object Hablar : Route("hablar")
    data object BuscarDispositivo : Route("buscar_dispositivo")
}