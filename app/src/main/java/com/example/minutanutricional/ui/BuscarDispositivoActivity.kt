package com.example.minutanutricional.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.minutanutricional.R

class BuscarDispositivoActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_dispositivo)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BuscarDispositivoFragment())
                .commit()
        }
    }
}