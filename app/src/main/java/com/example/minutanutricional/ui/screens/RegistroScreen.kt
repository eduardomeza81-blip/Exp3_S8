package com.example.minutanutricional.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minutanutricional.R
import com.example.minutanutricional.data.UsuariosData
import com.example.minutanutricional.model.Usuario
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import android.util.Patterns

@Composable
fun RegistroScreen(
    onCrearCuenta: () -> Unit,
    onBack: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    // ComboBox (Dropdown)
    val objetivos = listOf("Lectura de textos", "Escritura asistida", "Comunicación básica")
    var expanded by remember { mutableStateOf(false) }
    var objetivoSeleccionado by remember { mutableStateOf(objetivos.first()) }

    // Checklist (Checkboxes)
    var sinAzucar by remember { mutableStateOf(false) }
    var sinLactosa by remember { mutableStateOf(false) }
    var vegetariano by remember { mutableStateOf(false) }

    // Radio buttons
    val niveles = listOf("Bajo", "Medio", "Alto")
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
            painter = painterResource(id = R.drawable.registro),
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
            Text("Registro de usuario accesible", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; errorMsg=""},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it; errorMsg=""},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Correo") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it; errorMsg=""},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Contraseña") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Accesibilidad", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(6.dp))

           /*Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = objetivoSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccione el uso",
                        fontSize = 18.sp, fontWeight = FontWeight.Medium) },
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
            }*/
            @OptIn(ExperimentalMaterial3Api::class)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = objetivoSeleccionado,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(
                            "Tipo de uso",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    singleLine = true
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
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
            Text("Preferencias", style = MaterialTheme.typography.titleMedium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = sinAzucar, onCheckedChange = { sinAzucar = it })
                Text("Alto contraste")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = sinLactosa, onCheckedChange = { sinLactosa = it })
                Text("Texto grande")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = vegetariano, onCheckedChange = { vegetariano = it })
                Text("Interfaz simplicada")
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Nivel de asistencia", style = MaterialTheme.typography.titleMedium)

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
           // Button(onClick = onCrearCuenta, modifier = Modifier.fillMaxWidth()) {
           //     Text("Crear cuenta")
           // }
            if (errorMsg.isNotEmpty()) {
                Text(text = errorMsg, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }
            /*Button(
                onClick = {
                    UsuariosData.agregarUsuario(Usuario(correo = email, password=password))
                    onCrearCuenta()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }*/
            Button(
                onClick = {
                    val n = nombre.trim()
                    val c = email.trim().lowercase()
                    val p = password

                    // Validaciones básicas
                    if (n.isEmpty()) { errorMsg = "Ingresa tu nombre."; return@Button }
                    if (c.isEmpty()) { errorMsg = "Ingresa tu correo."; return@Button }
                    if (!Patterns.EMAIL_ADDRESS.matcher(c).matches()) {
                        errorMsg = "Correo no válido."
                        return@Button
                    }
                    if (p.isBlank()) { errorMsg = "Ingresa una contraseña."; return@Button }
                    if (p.length < 4) { errorMsg = "La contraseña debe tener al menos 4 caracteres."; return@Button }

                    // Límite de 5 usuarios
                    val ok = UsuariosData.agregarUsuario(Usuario(correo = c, password = p))
                    if (ok) {
                        errorMsg = ""
                        onCrearCuenta()
                    } else {
                        errorMsg = "Ya se registraron 5 usuarios. No puedes crear más."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Volver a Login")
            }
        }
    }
}
