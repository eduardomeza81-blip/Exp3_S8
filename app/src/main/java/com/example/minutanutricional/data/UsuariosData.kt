package com.example.minutanutricional.data

import com.example.minutanutricional.model.Usuario


object UsuariosData {

    private val usuarios = mutableListOf<Usuario>()

    fun agregarUsuario(usuario: Usuario): Boolean{
        return if(usuarios.size<5){
            usuarios.add(usuario)
            true
        }else{
            false
        }
    }
    fun validarUsuario(correo: String, password: String): Boolean{
        return usuarios.any {
            it.correo == correo && it.password == password
        }
    }
    fun cantidadUsuarios(): Int{
        return usuarios.size
    }
    fun existeCorreo(correo: String): Boolean {
        val c = correo.trim().lowercase()
        return usuarios.any { it.correo.trim().lowercase() == c }
    }
}