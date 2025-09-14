package net.fastboypay.demoflow.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun ConfigScreen(
    selectedEntryStep: EntryStep,
    onNavigateBack: () -> Unit,
    onProceed: (Map<String, String>) -> Unit
) {
    // Input states for A flow
    var a1Input by remember { mutableStateOf("") }
    var a2Input by remember { mutableStateOf("") }
    var a3Input by remember { mutableStateOf("") }
    var a4Input by remember { mutableStateOf("") }

    // Input states for B flow
    var b1Input by remember { mutableStateOf("") }
    var b2Input by remember { mutableStateOf("") }
    var b3Input by remember { mutableStateOf("") }

    // Determine which inputs to show based on entry step
    val showInputs = when (selectedEntryStep) {
        EntryStep.A1 -> listOf("A1")
        EntryStep.A2 -> listOf("A1")
        EntryStep.A3 -> listOf("A1", "A2")
        EntryStep.A4 -> listOf("A1", "A2", "A3")
        EntryStep.AFinal -> listOf("A1", "A2", "A3", "A4")
        EntryStep.B1 -> listOf("B1")
        EntryStep.B2 -> listOf("B1")
        EntryStep.B3 -> listOf("B1", "B2")
        EntryStep.BFinal -> listOf("B1", "B2", "B3")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configure ${selectedEntryStep.name}") },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Configure Data for ${selectedEntryStep.name}",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Enter the data for the steps that will be pre-filled:",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // A1 Input
            if ("A1" in showInputs) {
                OutlinedTextField(
                    value = a1Input,
                    onValueChange = { a1Input = it },
                    label = { Text("A1 Data") },
                    placeholder = { Text("Enter A1 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // A2 Input
            if ("A2" in showInputs) {
                OutlinedTextField(
                    value = a2Input,
                    onValueChange = { a2Input = it },
                    label = { Text("A2 Data") },
                    placeholder = { Text("Enter A2 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // A3 Input
            if ("A3" in showInputs) {
                OutlinedTextField(
                    value = a3Input,
                    onValueChange = { a3Input = it },
                    label = { Text("A3 Data") },
                    placeholder = { Text("Enter A3 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }


            // A4 Input
            if ("A4" in showInputs) {
                OutlinedTextField(
                    value = a4Input,
                    onValueChange = { a4Input = it },
                    label = { Text("A4 Data") },
                    placeholder = { Text("Enter A4 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // B1 Input
            if ("B1" in showInputs) {
                OutlinedTextField(
                    value = b1Input,
                    onValueChange = { b1Input = it },
                    label = { Text("B1 Data") },
                    placeholder = { Text("Enter B1 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // B2 Input
            if ("B2" in showInputs) {
                OutlinedTextField(
                    value = b2Input,
                    onValueChange = { b2Input = it },
                    label = { Text("B2 Data") },
                    placeholder = { Text("Enter B2 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // B3 Input
            if ("B3" in showInputs) {
                OutlinedTextField(
                    value = b3Input,
                    onValueChange = { b3Input = it },
                    label = { Text("B3 Data") },
                    placeholder = { Text("Enter B3 information...") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Proceed Button
            Button(
                onClick = {
                    val inputData = mutableMapOf<String, String>()
                    if ("A1" in showInputs) inputData["A1"] = a1Input
                    if ("A2" in showInputs) inputData["A2"] = a2Input
                    if ("A3" in showInputs) inputData["A3"] = a3Input
                    if ("A4" in showInputs) inputData["A4"] = a4Input
                    if ("B1" in showInputs) inputData["B1"] = b1Input
                    if ("B2" in showInputs) inputData["B2"] = b2Input
                    if ("B3" in showInputs) inputData["B3"] = b3Input
                    onProceed(inputData)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceed to ${selectedEntryStep.name}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigScreenPreview() {
    DemoFlowTheme {
        ConfigScreen(
            selectedEntryStep = EntryStep.AFinal,
            onNavigateBack = {},
            onProceed = {}
        )
    }
}
