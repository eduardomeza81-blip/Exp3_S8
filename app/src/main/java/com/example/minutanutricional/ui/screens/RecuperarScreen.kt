package com.example.minutanutricional.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.minutanutricional.R
import com.example.minutanutricional.services.AuthService

import android.util.Patterns
@Composable
fun RecuperarScreen(
    onBack: () -> Unit
) {
    val (correo, setCorreo) = remember { mutableStateOf("") }
    val (mensaje, setMensaje) = remember { mutableStateOf("") }
    /*    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )*/
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.recuperar),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        )
        {
            Text("Recuperar contraseña", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = {setCorreo(it)},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Correo") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    val c = correo.trim().lowercase()

                    if (c.isBlank()) {
                        setMensaje("Ingresa un correo.")
                        return@Button
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(c).matches()) {
                        setMensaje("Correo no válido.")
                        return@Button
                    }

                    AuthService.reset(
                        email = c,
                        ok = {
                            setMensaje("Si el correo existe, se enviaron instrucciones de recuperación.")
                        },
                        fail = { msg ->
                            setMensaje(msg)
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Recuperar")
            }
            if(mensaje.isNotEmpty()){
                Spacer(modifier = Modifier.height(12.dp))
                Text(mensaje)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onBack,modifier = Modifier.fillMaxWidth()){
                Text("Volver a Login")
            }
        }
    }
}
