package net.fastboypay.demoflow.features.setting

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
import net.fastboypay.demoflow.base.theme.DemoFlowTheme
import net.fastboypay.demoflow.features.home.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    mainViewModel: MainViewModel,
    onNavigateBack: () -> Unit
) {
    val isFull by mainViewModel.isFull.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Configuration",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Flow Configuration",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Full Flow Mode",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Switch(
                            checked = isFull,
                            onCheckedChange = { mainViewModel.updateIsFull(it) }
                        )
                    }
                    
                    Text(
                        text = if (isFull) "B Flow will include all steps (B1 → B2 → B3 → BFinal)" 
                               else "B Flow will skip B2 (B1 → B3 → BFinal)",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    DemoFlowTheme {
        // Preview with mock ViewModel
        SettingScreen(
            mainViewModel = MainViewModel(net.fastboypay.demoflow.data.preferences.AppPreferences(androidx.compose.ui.platform.LocalContext.current)),
            onNavigateBack = {}
        )
    }
}
