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
import net.fastboypay.demoflow.features.a.viewmodel.StepA2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepA2Screen(
    viewModel: StepA2ViewModel,
    onNavigateToA3: () -> Unit,
    onNavigateToA4: () -> Unit
) {
    val a2Text by viewModel.a2Text.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step A2") }
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
                text = "Step A2",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = a2Text,
                onValueChange = { viewModel.updateA2Text(it) },
                label = { Text("A2 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${a2Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Text(
                text = "Choose your path:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToA3()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Start 3 (Go to A3)")
            }
            
            Button(
                onClick = { 
                    onNavigateToA4()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Start 4 (Go to A4)")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepA2ScreenPreview() {
    DemoFlowTheme {
        StepA2Screen(
            viewModel = hiltViewModel(),
            onNavigateToA3 = {},
            onNavigateToA4 = {}
        )
    }
}
