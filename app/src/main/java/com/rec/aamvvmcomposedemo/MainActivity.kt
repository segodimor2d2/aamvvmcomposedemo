package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rec.aamvvmcomposedemo.ui.MainViewModel
import com.rec.aamvvmcomposedemo.ui.RgbUiState
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
                            .padding(24.dp, 16.dp)
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

    when (uiState) {

        RgbUiState.Loading -> {
            Text("Carregando LEDs...")
        }

        is RgbUiState.Error -> {
            Text((uiState as RgbUiState.Error).message)
        }

        is RgbUiState.Ready -> {

            val state = uiState as RgbUiState.Ready

            var r by remember { mutableStateOf(state.r.toFloat()) }
            var g by remember { mutableStateOf(state.g.toFloat()) }
            var b by remember { mutableStateOf(state.b.toFloat()) }

            Column {

                Text("Red: ${r.toInt()}")
                Slider(
                    value = r,
                    onValueChange = {
                        r = it
                        viewModel.updateRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    onValueChangeFinished = {
                        viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    valueRange = 0f..100f
                )

                Text("Green: ${g.toInt()}")
                Slider(
                    value = g,
                    onValueChange = {
                        g = it
                        viewModel.updateRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    onValueChangeFinished = {
                        viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    valueRange = 0f..100f
                )

                Text("Blue: ${b.toInt()}")
                Slider(
                    value = b,
                    onValueChange = {
                        b = it
                        viewModel.updateRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    onValueChangeFinished = {
                        viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                    },
                    valueRange = 0f..100f
                )
            }
        }
    }
}
