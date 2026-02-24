package com.example.minutanutricional.model

data class Usuario(
    var uid: String = "",
    var nombre: String = "",
    var correo: String = "",
    var objetivo: String = "",
    var nivelAsistencia: String = "",
    var altoContraste: Boolean = false,
    var textoGrande: Boolean = false,
    var interfazSimplificada: Boolean = false
)