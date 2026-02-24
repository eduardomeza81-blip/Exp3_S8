package com.example.minutanutricional.services

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object DbService {
    val root: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }
}