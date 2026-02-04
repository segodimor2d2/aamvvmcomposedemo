package com.rec.aamvvmcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            val ledState by viewModel.ledState.collectAsState()
            var isOn by remember { mutableStateOf(false) }

            AaMvvmComposeDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {

                        Switch(
                            checked = isOn,
                            onCheckedChange = { checked ->
                                isOn = checked
                                viewModel.setLed(
                                    ledId = 2,
                                    percent = if (checked) 100 else 0
                                )
                            }
                        )

                        Spacer(Modifier.padding(16.dp))

                        if (ledState == null) {
                            Text("Nenhum dado enviado")
                        } else {
                            Text("Percent: ${ledState!!.percent}")
                            Text("Duty: ${ledState!!.duty}")
                        }

                    }
                }
            }
        }
    }
}
