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
import net.fastboypay.demoflow.features.b.viewmodel.StepB2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepB2Screen(
    viewModel: StepB2ViewModel,
    onNavigateToB3: () -> Unit
) {
    val b2Text by viewModel.b2Text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step B2") }
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
                text = "Step B2",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = b2Text,
                onValueChange = { viewModel.updateB2Text(it) },
                label = { Text("B2 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${b2Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToB3()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue to B3")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepB2ScreenPreview() {
    DemoFlowTheme {
        StepB2Screen(
            viewModel = hiltViewModel(),
            onNavigateToB3 = {}
        )
    }
}
