package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rec.aamvvmcomposedemo.ui.LedUiState
import com.rec.aamvvmcomposedemo.ui.MainViewModel
import com.rec.aamvvmcomposedemo.ui.theme.AaMvvmComposeDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val viewModel: MainViewModel = viewModel()
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
                        LedControls()
                    }
                }
            }
        }
    }
}


@Composable
fun LedControls(viewModel: MainViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {

        LedRow(ledId = 1, uiState, viewModel)
        LedRow(ledId = 2, uiState, viewModel)
        LedRow(ledId = 3, uiState, viewModel)

        Spacer(Modifier.padding(16.dp))

        when (uiState) {
            is LedUiState.Loading -> {
                val state = uiState as LedUiState.Loading
                Text("Atualizando LED ${state.ledId}...")
            }

            is LedUiState.Success -> {
                val state = uiState as LedUiState.Success
                Text("LED ${state.ledId} → ${state.percent}% (duty ${state.duty})")
            }

            is LedUiState.Error -> {
                Text((uiState as LedUiState.Error).message)
            }

            LedUiState.Idle -> {
                Text("Pronto")
            }
        }
    }
}


@Composable
fun LedRow(
    ledId: Int,
    uiState: LedUiState,
    viewModel: MainViewModel
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text("LED $ledId")

        Button(
            onClick = { viewModel.setLed(ledId, 100) },
            enabled = uiState !is LedUiState.Loading
        ) {
            Text("ON")
        }

        Button(
            onClick = { viewModel.setLed(ledId, 0) },
            enabled = uiState !is LedUiState.Loading
        ) {
            Text("OFF")
        }
    }
}
