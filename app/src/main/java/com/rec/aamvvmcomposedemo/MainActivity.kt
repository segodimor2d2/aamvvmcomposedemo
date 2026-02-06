package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
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


            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2200.dp)
            ) {
                VerticalSlider("R", Color.Red, r.toInt()) { newR ->
                    r = newR.toFloat()
                    viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                }

                VerticalSlider("G", Color.Green, g.toInt()) { newG ->
                    g = newG.toFloat()
                    viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                }

                VerticalSlider("B", Color.Blue, b.toInt()) { newB ->
                    b = newB.toFloat()
                    viewModel.commitRgb(r.toInt(), g.toInt(), b.toInt())
                }
            }
        }
    }
}

@Composable
fun VerticalSlider(
    label: String,
    color: Color,
    value: Int,
    onRelease: (Int) -> Unit
) {
    var local by remember { mutableStateOf(value.toFloat()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Text(
            text = label,
            color = color
        )

        Slider(
            value = local,
            onValueChange = { local = it },
            onValueChangeFinished = {
                onRelease(local.toInt())
            },
            valueRange = 0f..100f,
            modifier = Modifier
                .height(400.dp)
                .width(40.dp)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            minWidth = constraints.minHeight,
                            maxWidth = constraints.maxHeight,
                            minHeight = constraints.minWidth,
                            maxHeight = constraints.maxWidth,
                        )
                    )

                    layout(placeable.height, placeable.width) {
                        placeable.place(
                            x = -(placeable.width - placeable.height) / 2,
                            y = (placeable.width - placeable.height) / 2
                        )
                    }
                }
                .rotate(-90f)
        )

        Text(
            text = "${local.toInt()}%",
            color = color
        )
    }
}
