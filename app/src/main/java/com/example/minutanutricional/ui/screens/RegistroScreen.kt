package com.example.minutanutricional.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.minutanutricional.R


@Composable
fun RegistroScreen(
    onCrearCuenta: () -> Unit,
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // ComboBox (Dropdown)
    val objetivos = listOf("Bajar peso", "Mantener", "Subir masa")
    var expanded by remember { mutableStateOf(false) }
    var objetivoSeleccionado by remember { mutableStateOf(objetivos.first()) }

    // Checklist (Checkboxes)
    var sinAzucar by remember { mutableStateOf(false) }
    var sinLactosa by remember { mutableStateOf(false) }
    var vegetariano by remember { mutableStateOf(false) }

    // Radio buttons
    val niveles = listOf("Baja", "Media", "Alta")
    var nivelActividad by remember { mutableStateOf(niveles.first()) }

/*    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    )*/
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.portada),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        )
        {
            Text("Registro de usuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Correo") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Contraseña") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Objetivo nutricional (ComboBox)", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(6.dp))

            // IMPORTANTE: Box para anclar el Dropdown y evitar errores/posición rara
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = objetivoSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Objetivo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    singleLine = true
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    objetivos.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                objetivoSeleccionado = item
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Preferencias (CheckList)", style = MaterialTheme.typography.titleMedium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = sinAzucar, onCheckedChange = { sinAzucar = it })
                Text("Sin azúcar")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = sinLactosa, onCheckedChange = { sinLactosa = it })
                Text("Sin lactosa")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = vegetariano, onCheckedChange = { vegetariano = it })
                Text("Vegetariano")
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Nivel de actividad (Radio Buttons)", style = MaterialTheme.typography.titleMedium)

            niveles.forEach { nivel ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (nivelActividad == nivel),
                        onClick = { nivelActividad = nivel }
                    )
                    Text(nivel)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onCrearCuenta, modifier = Modifier.fillMaxWidth()) {
                Text("Crear cuenta")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Volver a Login")
            }
        }
    }
}
