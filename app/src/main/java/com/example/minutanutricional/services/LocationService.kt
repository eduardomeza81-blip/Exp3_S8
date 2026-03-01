package com.example.minutanutricional.services

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


object LocationService {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun saveLocation(
        lat: Double,
        lon: Double,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: "anon"

        val data = hashMapOf(
            "uid" to uid,
            "lat" to lat,
            "lon" to lon,
            "timestamp" to Timestamp.now()
        )

        db.collection("ubicaciones")
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onError(e.message ?: "Error guardando ubicación") }
    }
    fun getMyLocations(
        limit: Long = 10,
        onSuccess: (List<Map<String, Any>>) -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            onError("Usuario no autenticado.")
            return
        }

        db.collection("ubicaciones")
            .whereEqualTo("uid", uid)
            .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(limit)
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.documents.mapNotNull { it.data }
                onSuccess(list)
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Error leyendo ubicaciones")
            }
    }
    fun clearMyLocations(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: "anon"

        db.collection("ubicaciones")
            .whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { snap ->
                val batch = db.batch()
                snap.documents.forEach { doc ->
                    batch.delete(doc.reference)
                }
                batch.commit()
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e.message ?: "Error borrando historial") }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Error consultando historial")
            }
    }
}