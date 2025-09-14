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
import net.fastboypay.demoflow.features.a.viewmodel.StepA3ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepA3Screen(
    viewModel: StepA3ViewModel,
    onNavigateToA4: () -> Unit
) {
    val a3Text by viewModel.a3Text.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Step A3") }
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
                text = "Step A3",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            OutlinedTextField(
                value = a3Text,
                onValueChange = { viewModel.updateA3Text(it) },
                label = { Text("A3 Data") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Current: ${a3Text}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Button(
                onClick = { 
                    onNavigateToA4()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue to A4")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepA3ScreenPreview() {
    DemoFlowTheme {
        StepA3Screen(
            viewModel = hiltViewModel(),
            onNavigateToA4 = {}
        )
    }
}
