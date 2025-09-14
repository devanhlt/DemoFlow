package net.fastboypay.demoflow.features.a.screens

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
import net.fastboypay.demoflow.features.a.viewmodel.StepA4ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepA4Screen(
    viewModel: StepA4ViewModel,
    onNavigateToAFinal: () -> Unit
) {
    val a4Text by viewModel.a4Text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step A4") }
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
                text = "Step A4",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = a4Text,
                onValueChange = { viewModel.updateA4Text(it) },
                label = { Text("A4 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${a4Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToAFinal()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue to A Final")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepA4ScreenPreview() {
    DemoFlowTheme {
        StepA4Screen(
            viewModel = hiltViewModel(),
            onNavigateToAFinal = {}
        )
    }
}
