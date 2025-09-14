package net.fastboypay.demoflow.common.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import net.fastboypay.demoflow.data.model.AData
import net.fastboypay.demoflow.data.model.BData
import net.fastboypay.demoflow.data.model.EntryStep
import net.fastboypay.demoflow.navigation.MainNavGraph
import net.fastboypay.demoflow.base.theme.DemoFlowTheme
import net.fastboypay.demoflow.features.home.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoFlowTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val mainViewModel: MainViewModel = hiltViewModel()

                MainNavGraph(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    onStartAFlow = {
                        val aData = AData()
                        val intent = Intent(context, XActivity::class.java).apply {
                            putExtra("entry_step", EntryStep.A1.name)
                            putExtra("data", Gson().toJson(aData))
                        }
                        context.startActivity(intent)
                    },
                    onStartBFlow = {
                        val bData = BData()
                        val intent = Intent(context, XActivity::class.java).apply {
                            putExtra("entry_step", EntryStep.B1.name)
                            putExtra("data", Gson().toJson(bData))
                        }
                        context.startActivity(intent)
                    },
                    onGoToEntryStep = { entryStep, dataJson ->
                        val intent = Intent(context, XActivity::class.java).apply {
                            putExtra("entry_step", entryStep.name)
                            putExtra("data", dataJson)
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}