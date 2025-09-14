package net.fastboypay.demoflow.features.b.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import net.fastboypay.demoflow.base.theme.DemoFlowTheme
import net.fastboypay.demoflow.features.b.viewmodel.StepB3ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepB3Screen(
    viewModel: StepB3ViewModel,
    onNavigateToBFinal: () -> Unit
) {
    val b3Text by viewModel.b3Text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step B3") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Step B3",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = b3Text,
                onValueChange = { viewModel.updateB3Text(it) },
                label = { Text("B3 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${b3Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToBFinal()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue to B Final")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepB3ScreenPreview() {
    DemoFlowTheme {
        StepB3Screen(
            viewModel = hiltViewModel(),
            onNavigateToBFinal = {}
        )
    }
}
