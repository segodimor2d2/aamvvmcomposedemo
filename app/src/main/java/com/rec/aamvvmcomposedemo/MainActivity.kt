package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rec.aamvvmcomposedemo.ui.MainViewModel
import com.rec.aamvvmcomposedemo.ui.theme.AaMvvmComposeDemoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: MainViewModel = viewModel()
            val message by viewModel.message.collectAsState()
            val text by viewModel.text.collectAsState()

            val validaText by viewModel.validaText.collectAsState()
            val isValid by viewModel.isValid.collectAsState()

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val isFormValid by viewModel.isFormValid.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val loginResult by viewModel.loginResult.collectAsState()


            AaMvvmComposeDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                    ) {
                        Text(text = message)

                        Button(
                            onClick = { viewModel.changeMessage() }
                        ) {
                            Text("Alterar mensagem")
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        TextField(
                            value = text,
                            onValueChange = { newValue -> viewModel.onTextChange(newValue) },
                            label = { Text("Digite algo") }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Text(text = "Você digitou:")
                        Text(text = text)


                        Spacer(modifier = Modifier.padding(8.dp))
                        TextField(
                            value = validaText,
                            onValueChange = { viewModel.onValidaTextChange(it) },
                            label = { Text("Digite pelo menos 3 caracteres") }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = if (isValid) "Texto válido ✅" else "Texto inválido ❌"
                        )

                        Spacer(modifier = Modifier.padding(8.dp))
                        Button(
                            onClick = { viewModel.apagaMessage() }
                        ) {
                            Text("reset")
                        }









                        Spacer(modifier = Modifier.height(24.dp))

                        TextField(
                            value = email,
                            onValueChange = { viewModel.onEmailChange(it) },
                            label = { Text("Email") }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        TextField(
                            value = password,
                            onValueChange = { viewModel.onPasswordChange(it) },
                            label = { Text("Senha") }
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        Button(
                            enabled = isFormValid && !isLoading,
                            onClick = { viewModel.login() }
                        ) {
                            Text(if (isLoading) "Entrando..." else "Entrar")
                        }

                        Spacer(modifier = Modifier.padding(16.dp))

                        if (loginResult != null) {
                            Text(text = loginResult!!)
                        }

                    }
                }
            }
        }
    }
}





