package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.rec.aamvvmcomposedemo.ui.LoginUiState
import com.rec.aamvvmcomposedemo.ui.MainViewModel
import com.rec.aamvvmcomposedemo.ui.theme.AaMvvmComposeDemoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: MainViewModel = viewModel()

            val email by viewModel.email.collectAsState()
            val password by viewModel.password.collectAsState()
            val uiState by viewModel.uiState.collectAsState()

            AaMvvmComposeDemoTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                    ) {

                        TextField(
                            value = email,
                            onValueChange = viewModel::onEmailChange,
                            label = { Text("Email") }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        TextField(
                            value = password,
                            onValueChange = viewModel::onPasswordChange,
                            label = { Text("Senha") }
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        Button(
                            onClick = { viewModel.login() },
                            enabled = uiState !is LoginUiState.Loading
                        ) {
                            Text("Entrar")
                        }

                        Spacer(modifier = Modifier.padding(16.dp))

                        when (uiState) {
                            is LoginUiState.Idle -> {
                                Text("Digite seus dados")
                            }

                            is LoginUiState.Loading -> {
                                Text("Carregando...")
                            }

                            is LoginUiState.Success -> {
                                Text((uiState as LoginUiState.Success).message)
                            }

                            is LoginUiState.Error -> {
                                Text((uiState as LoginUiState.Error).message)
                            }
                        }

                        Spacer(modifier = Modifier.padding(16.dp))

                        Button(onClick = { viewModel.reset() }) {
                            Text("Reset")
                        }

                    }
                }
            }
        }
    }
}





