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
                        Button(
                            onClick = { viewModel.apagaMessage() }
                        ) {
                            Text("reset")
                        }
                    }
                }
            }
        }
    }
}


