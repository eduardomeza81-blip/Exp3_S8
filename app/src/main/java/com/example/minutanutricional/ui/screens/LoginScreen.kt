package com.example.minutanutricional.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.minutanutricional.R
import com.example.minutanutricional.data.UsuariosData

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onGoToRegistro: () -> Unit,
    onGoToRecuperar: () -> Unit
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (error, setError) = remember { mutableStateOf("") }

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
            painter = painterResource(id = R.drawable.portada),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,

        )
        {
            Text(
                text = "TeAcompaño",
                color = Color.DarkGray,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier.fillMaxWidth(),
                 colors = CardDefaults.cardColors(
                     containerColor = Color.White.copy(alpha = 0.4f)
                 )
                ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = setEmail,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Correo") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = setPassword,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(
//                        onClick = onLogin,
//                        modifier = Modifier.fillMaxWidth()
//                    )
                    if(error.isNotEmpty()){
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Button(
                        onClick = {
                            val ok = UsuariosData.validarUsuario(correo = email, password=password)
                            if (ok){
                                setError("Satisfactorio")
                                onLogin()
                            }else{
                                setError("Credenciales inválidas")
                            }
                        }, modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFF6D4FB3), contentColor = Color.White
                        )
                    ) {
                        Text("Ingresar")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onGoToRecuperar() },
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Crear cuenta",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onGoToRegistro() },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
