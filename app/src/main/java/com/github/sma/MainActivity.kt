package com.github.sma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.sma.data.ProductDatabase
import com.github.sma.data.ProductRepository
import com.github.sma.ui.screen.AddEditItemScreen
import com.github.sma.ui.screen.DashboardScreen
import com.github.sma.ui.screen.ItemDetailScreen
import com.github.sma.ui.screen.Route
import com.github.sma.ui.screen.SettingsScreen
import com.github.sma.ui.screen.StockListScreen
import com.github.sma.ui.screen.TransactionHistoryScreen
import com.github.sma.ui.theme.StockmanagerappTheme
import com.github.sma.ui.vm.MainViewModel
import com.github.sma.ui.vm.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory =
            ViewModelFactory(ProductRepository(ProductDatabase.getInstance(this).productDao()))
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        setContent {
            StockmanagerappTheme {
                MainNavController(viewModel)
            }
        }
    }
}

@Composable
fun MainNavController(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.Dashboard.route) {
        composable(Route.Dashboard.route) {
            DashboardScreen(
                onNavigateToStockList = { navController.navigate(Route.StockList.route) },
                onNavigateToAddItem = { navController.navigate(Route.AddItem.route) },
                onNavigateToTransactions = { navController.navigate(Route.Transactions.route) },
                stockItems = mainViewModel.stockItems.toMutableStateList()
            )
        }
        composable(Route.StockList.route) {
            StockListScreen(
                items = mainViewModel.stockItems,
                onAddItemClick = {
                    navController.navigate(Route.AddItem.route)
                },
                onItemClick = {
                    navController.navigate(Route.ItemDetail.createRoute(it.id))
                }
            )
        }
        composable(Route.AddItem.route) {
            AddEditItemScreen(
                onSaveItem = {
                    mainViewModel.addStockItem(it)
                    navController.popBackStack()
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Route.EditItem.routeWithArgs) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString(Route.EditItem.itemIdArg)
            val itemToEdit = itemId?.let { mainViewModel.getStockItemById(it.toInt()) }
            AddEditItemScreen(
                itemToEdit = itemToEdit,
                onSaveItem = {
                    mainViewModel.updateStockItem(it)
                    navController.popBackStack()
                },
                onNavigateBack = { navController.popBackStack() },
            )
        }
        composable(Route.Transactions.route) {
            TransactionHistoryScreen(
                transactions = emptyList(),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Route.Settings.route) {
            SettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(Route.ItemDetail.routeWithArgs) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getString(Route.ItemDetail.itemIdArg)
            val item = itemId?.let { mainViewModel.getStockItemById(it.toInt()) }

            ItemDetailScreen(
                item = item,
                onNavigateBack = { navController.popBackStack() },
                onEditClick = {
                    item?.id?.let { navController.navigate(Route.EditItem.createRoute(it)) }
                },
                onDeleteClick = {
                    item?.let { mainViewModel.deleteStockItem(it); navController.popBackStack() }
                },
                onAddStock = { stock, amount ->
                    item?.let {
                        val updatedItem = it.copy(quantity = it.quantity + amount)
                        mainViewModel.updateStockItem(updatedItem)
                    }
                },
                onReduceStock = { stock, amount ->
                    item?.let {
                        val updatedItem = it.copy(quantity = it.quantity - amount)
                        mainViewModel.updateStockItem(updatedItem)
                    }
                }
            )
        }
    }
}
