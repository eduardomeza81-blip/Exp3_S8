package com.example.minutanutricional.services

import com.google.firebase.auth.FirebaseAuth

object AuthService {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    fun currentUid(): String? = auth.currentUser?.uid

    fun register(
        email: String,
        pass: String,
        ok: (String) -> Unit,
        fail: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                val uid = it.user?.uid
                if (uid == null) fail("No se pudo obtener UID") else ok(uid)
            }
            .addOnFailureListener { fail(it.message ?: "Error registrando") }
    }

    fun login(
        email: String,
        pass: String,
        ok: () -> Unit,
        fail: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener { ok() }
            .addOnFailureListener { fail(it.message ?: "Error login") }
    }

    fun reset(
        email: String,
        ok: () -> Unit,
        fail: (String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { ok() }
            .addOnFailureListener { fail(it.message ?: "Error enviando correo") }
    }

    fun logout() = auth.signOut()
}