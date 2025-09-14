package net.fastboypay.demoflow.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.fastboypay.demoflow.features.home.HomeScreen
import net.fastboypay.demoflow.features.home.ConfigScreen
import net.fastboypay.demoflow.features.setting.SettingScreen
import net.fastboypay.demoflow.features.home.StartScreen
import net.fastboypay.demoflow.features.home.MainViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    onStartAFlow: () -> Unit,
    onStartBFlow: () -> Unit,
    onGoToEntryStep: (net.fastboypay.demoflow.data.model.EntryStep, String) -> Unit
) {
    val isFull by mainViewModel.isFull.collectAsState()
    NavHost(
        navController = navController,
        startDestination = MainDestinations.HOME
    ) {
        composable(MainDestinations.HOME) {
            HomeScreen(
                onNavigateToSettings = {
                    navController.navigate(MainDestinations.SETTINGS)
                },
                onNavigateToStart = {
                    navController.navigate(MainDestinations.START)
                },
                onConfigureEntryStep = { entryStep ->
                    navController.navigate("${MainDestinations.CONFIG}/${entryStep.name}")
                }
            )
        }
        
        composable(MainDestinations.SETTINGS) {
            SettingScreen(
                mainViewModel = mainViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(MainDestinations.START) {
            StartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onStartAFlow = onStartAFlow,
                onStartBFlow = onStartBFlow
            )
        }
        
        composable(
            route = "${MainDestinations.CONFIG}/{entryStep}",
            arguments = listOf(
                androidx.navigation.navArgument("entryStep") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->
            val entryStepName = backStackEntry.arguments?.getString("entryStep") ?: "A1"
            val entryStep = try {
                net.fastboypay.demoflow.data.model.EntryStep.valueOf(entryStepName)
            } catch (e: IllegalArgumentException) {
                net.fastboypay.demoflow.data.model.EntryStep.A1
            }
            
            ConfigScreen(
                selectedEntryStep = entryStep,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onProceed = { inputs ->
                    val dataJson = mainViewModel.createDataFromInputs(entryStep, inputs)
                    onGoToEntryStep(entryStep, dataJson)
                }
            )
        }
        
    }
}
