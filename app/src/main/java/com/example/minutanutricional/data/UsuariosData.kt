package com.example.minutanutricional.data

import androidx.compose.runtime.mutableStateOf
import com.example.minutanutricional.model.Usuario


object UsuariosData {
    val usuarios = mutableListOf(
        Usuario("usuario1@accesoapp.cl","1234"),
        Usuario("usuario2@accesoapp.cl","1234"),
        Usuario("usuario3@accesoapp.cl","1234"),
        Usuario("usuario4@accesoapp.cl","1234"),
        Usuario("usuario5@accesoapp.cl","1234"),
    )

    fun agregarUsuario(usuario: Usuario){
        if(usuarios.size<5){
            usuarios.add(usuario)
        }
    }
    fun validarUsuario(correo: String, password: String): Boolean{
        return usuarios.any { it.correo == correo && it.password == password}
    }
}