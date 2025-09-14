package net.fastboypay.demoflow.features.b.navigation

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import net.fastboypay.demoflow.data.model.BData
import net.fastboypay.demoflow.data.model.EntryStep
import net.fastboypay.demoflow.features.b.screens.*
import net.fastboypay.demoflow.features.b.viewmodel.*

@Composable
fun BNavGraph(
    navController: NavHostController,
    entryStep: EntryStep,
    bData: BData,
    isFull: Boolean,
    onFinish: () -> Unit = {}
) {
    
    val initialDataJson = Gson().toJson(bData)
    
    NavHost(
        navController = navController,
        startDestination = when (entryStep) {
            EntryStep.B1 -> "${BDestinations.STEP_B1}?data=$initialDataJson"
            EntryStep.B2 -> "${BDestinations.STEP_B2}?data=$initialDataJson"
            EntryStep.B3 -> "${BDestinations.STEP_B3}?data=$initialDataJson"
            EntryStep.BFinal -> "${BDestinations.STEP_B_FINAL}?data=$initialDataJson"
            else -> "${BDestinations.STEP_B1}?data=$initialDataJson"
        }
    ) {
        composable(
            route = "${BDestinations.STEP_B1}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepB1ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(bData, isFull)
                }
            }
            StepB1Screen(
                viewModel = viewModel,
                onNavigateToB2 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${BDestinations.STEP_B2}?data=$updatedData")
                },
                onNavigateToB3 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${BDestinations.STEP_B3}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${BDestinations.STEP_B2}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepB2ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(bData)
                }
            }
            StepB2Screen(
                viewModel = viewModel,
                onNavigateToB3 = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${BDestinations.STEP_B3}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${BDestinations.STEP_B3}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepB3ViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(bData)
                }
            }
            StepB3Screen(
                viewModel = viewModel,
                onNavigateToBFinal = {
                    val updatedData = viewModel.getDataAsJson()
                    navController.navigate("${BDestinations.STEP_B_FINAL}?data=$updatedData")
                }
            )
        }
        
        composable(
            route = "${BDestinations.STEP_B_FINAL}?data={data}",
            arguments = listOf(
                navArgument("data") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val dataJson = backStackEntry.arguments?.getString("data") ?: ""
            val viewModel: StepBFinalViewModel = hiltViewModel()
            LaunchedEffect(dataJson) {
                if (dataJson.isNotEmpty()) {
                    viewModel.initializeFromJson(dataJson)
                } else {
                    viewModel.initializeData(bData)
                }
            }
            StepBFinalScreen(
                viewModel = viewModel,
                onFinish = {
                    // Call the finish callback to return to StartFlow screen
                    onFinish()
                }
            )
        }
    }
}