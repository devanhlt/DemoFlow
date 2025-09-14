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
import net.fastboypay.demoflow.features.b.viewmodel.StepB1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepB1Screen(
    viewModel: StepB1ViewModel,
    onNavigateToB2: () -> Unit,
    onNavigateToB3: () -> Unit
) {
    val isFull by viewModel.isFull.collectAsState()
    val b1Text by viewModel.b1Text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step B1") }
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
                text = "Step B1",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = b1Text,
                onValueChange = { viewModel.updateB1Text(it) },
                label = { Text("B1 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${b1Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Configuration:",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Full Flow Mode: ${if (isFull) "Enabled" else "Disabled"}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = if (isFull) "Path: B1 → B2 → B3 → BFinal" 
                               else "Path: B1 → B3 → BFinal",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            
            if (isFull) {
                Button(
                    onClick = { 
                        onNavigateToB2()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Continue to B2 (Full Flow)")
                }
            } else {
                Button(
                    onClick = { 
                        onNavigateToB3()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Skip to B3 (Short Flow)")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepB1ScreenPreview() {
    DemoFlowTheme {
        StepB1Screen(
            viewModel = hiltViewModel(),
            onNavigateToB2 = {},
            onNavigateToB3 = {}
        )
    }
}
