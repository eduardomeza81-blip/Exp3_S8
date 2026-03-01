package com.example.minutanutricional.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun HomeMenuScreen(
    onEscribir: () -> Unit,
    onHablar: () -> Unit,
    onBuscarDispositivo: () -> Unit,
    onLogout: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 520.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Encabezado
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
            ) {
                Text(
                    text = "Menú principal",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Elige una opción",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
                )
            }

            // Botones principales
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                BigMenuButton(
                    text = "Escribir",
                    icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
                    onClick = onEscribir
                )
                BigMenuButton(
                    text = "Hablar",
                    icon = { Icon(Icons.Filled.RecordVoiceOver, contentDescription = null) },
                    onClick = onHablar
                )
                BigMenuButton(
                    text = "Buscar dispositivo",
                    icon = { Icon(Icons.Filled.LocationOn, contentDescription = null) },
                    onClick = onBuscarDispositivo
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Cerrar sesión (alerta)
                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 56.dp),
                    shape = shape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Icon(Icons.Filled.Logout, contentDescription = null)
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Cerrar sesión",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            // Pie opcional (accesibilidad)
            Text(
                text = "Tip: toca una vez para seleccionar.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Composable
private fun BigMenuButton(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp),
        shape = shape
    ) {
        icon()
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}