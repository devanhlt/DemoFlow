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
import net.fastboypay.demoflow.features.a.viewmodel.StepA1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepA1Screen(
    viewModel: StepA1ViewModel,
    onNavigateToA2: () -> Unit
) {
    val a1Text by viewModel.a1Text.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step A1") }
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
                text = "Step A1",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = a1Text,
                onValueChange = { viewModel.updateA1Text(it) },
                label = { Text("A1 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${a1Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToA2()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue to A2")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepA1ScreenPreview() {
    DemoFlowTheme {
        StepA1Screen(
            viewModel = hiltViewModel(),
            onNavigateToA2 = {}
        )
    }
}
