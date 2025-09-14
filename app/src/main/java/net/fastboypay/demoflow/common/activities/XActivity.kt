package net.fastboypay.demoflow.common.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import net.fastboypay.demoflow.data.model.AData
import net.fastboypay.demoflow.data.model.BData
import net.fastboypay.demoflow.data.model.EntryStep
import net.fastboypay.demoflow.data.preferences.AppPreferences
import net.fastboypay.demoflow.features.a.navigation.ANavGraph
import net.fastboypay.demoflow.features.b.navigation.BNavGraph
import net.fastboypay.demoflow.base.theme.DemoFlowTheme

@AndroidEntryPoint
class XActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val entryStepString = intent.getStringExtra("entry_step") ?: EntryStep.A1.name
        val dataJson = intent.getStringExtra("data") ?: ""
        val entryStep = try {
            EntryStep.valueOf(entryStepString)
        } catch (e: IllegalArgumentException) {
            EntryStep.A1
        }
        
        setContent {
            DemoFlowTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val appPreferences = remember { AppPreferences(context) }
                val isFull = remember { appPreferences.isFull }
                
                when (entryStep) {
                    EntryStep.A1, EntryStep.A2, EntryStep.A3, EntryStep.A4, EntryStep.AFinal -> {
                        val aData = try {
                            Gson().fromJson(dataJson, AData::class.java) ?: AData()
                        } catch (e: Exception) {
                            AData()
                        }
                        ANavGraph(
                            navController = navController,
                            entryStep = entryStep,
                            aData = aData,
                            onFinish = {
                                // Finish this activity to return to StartFlow screen
                                finish()
                            }
                        )
                    }
                    EntryStep.B1, EntryStep.B2, EntryStep.B3, EntryStep.BFinal -> {
                        val bData = try {
                            Gson().fromJson(dataJson, BData::class.java) ?: BData()
                        } catch (e: Exception) {
                            BData()
                        }
                        BNavGraph(
                            navController = navController,
                            entryStep = entryStep,
                            bData = bData,
                            isFull = isFull,
                            onFinish = {
                                // Finish this activity to return to StartFlow screen
                                finish()
                            }
                        )
                    }
                }
            }
        }
    }
}
