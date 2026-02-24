package com.example.minutanutricional.services

import com.example.minutanutricional.model.Usuario

object UserService {

    fun saveProfile(
        user: Usuario,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        DbService.root
            .child("users")
            .child(user.uid)
            .setValue(user)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                onError(e.message ?: "Error guardando perfil")
            }
    }
}