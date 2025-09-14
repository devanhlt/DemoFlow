package net.fastboypay.demoflow.features.home

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
import net.fastboypay.demoflow.data.model.EntryStep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToStart: () -> Unit,
    onConfigureEntryStep: (EntryStep) -> Unit
) {
    val viewModel: MainViewModel = hiltViewModel()
    val selectedEntryStep by viewModel.selectedEntryStep.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DemoFlow") }
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
                text = "Welcome to DemoFlow",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Entry Step Selection Dropdown
            Text(
                text = "Select Entry Step:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedEntryStep.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Entry Step") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        EntryStep.entries.forEach { entryStep ->
                            DropdownMenuItem(
                                text = { Text(entryStep.name) },
                                onClick = {
                                    viewModel.updateSelectedEntryStep(entryStep)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
            
            // Configure Button
            Button(
                onClick = { onConfigureEntryStep(selectedEntryStep) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Configure & Go")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onNavigateToSettings,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Settings")
            }
            
            Button(
                onClick = onNavigateToStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Start")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DemoFlowTheme {
        HomeScreen(
            onNavigateToSettings = {},
            onNavigateToStart = {},
            onConfigureEntryStep = {}
        )
    }
}
