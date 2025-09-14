package net.fastboypay.demoflow.features.a.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import net.fastboypay.demoflow.data.model.AData
import net.fastboypay.demoflow.data.model.EntryStep
import net.fastboypay.demoflow.features.a.screens.*
import net.fastboypay.demoflow.features.a.viewmodel.*

@Composable
fun ANavGraph(
    navController: NavHostController,
    entryStep: EntryStep,
    aData: AData,
    onFinish: () -> Unit = {}
) {
    
    val initialDataJson = Gson().toJson(aData)
    
    NavHost(
        navController = navController,
        startDestination = when (entryStep) {
            EntryStep.A1 -> "${ADestinations.STEP_A1}?data=$initialDataJson"
            EntryStep.A2 -> "${ADestinations.STEP_A2}?data=$initialDataJson"
            EntryStep.A3 -> "${ADestinations.STEP_A3}?data=$initialDataJson"
            EntryStep.A4 -> "${ADestinations.STEP_A4}?data=$initialDataJson"
            EntryStep.AFinal -> "${ADestinations.STEP_A_FINAL}?data=$initialDataJson"
            else -> "${ADestinations.STEP_A1}?data=$initialDataJson"
        }
    ) {
        composable(
            route = "${ADestinations.STEP_A1}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepA1ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(aData)
                }
            }
            StepA1Screen(
                viewModel = viewModel,
                onNavigateToA2 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${ADestinations.STEP_A2}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${ADestinations.STEP_A2}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepA2ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(aData)
                }
            }
            StepA2Screen(
                viewModel = viewModel,
                onNavigateToA3 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${ADestinations.STEP_A3}?data=$updatedData")
                },
                onNavigateToA4 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${ADestinations.STEP_A4}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${ADestinations.STEP_A3}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepA3ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(aData)
                }
            }
            StepA3Screen(
                viewModel = viewModel,
                onNavigateToA4 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${ADestinations.STEP_A4}?data=$updatedData")
                }
            )
        }
        
        
        composable(
            route = "${ADestinations.STEP_A4}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepA4ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(aData)
                }
            }
            StepA4Screen(
                viewModel = viewModel,
                onNavigateToAFinal = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${ADestinations.STEP_A_FINAL}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${ADestinations.STEP_A_FINAL}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepAFinalViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(aData)
                }
            }
            StepAFinalScreen(
                viewModel = viewModel,
                onFinish = {
                    // Call the finish callback to return to StartFlow screen
                    onFinish()
                }
            )
        }
    }
}