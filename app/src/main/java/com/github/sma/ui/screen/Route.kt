package com.github.sma.ui.screen

sealed class Route(val route: String) {
    object Dashboard : Route("dashboard")
    object StockList : Route("stockList")
    object AddItem : Route("addItem")
    object EditItem : Route("editItem") {
        const val itemIdArg = "productId"
        val routeWithArgs: String
            get() = "$route/{$itemIdArg}"

        fun createRoute(itemId: String): String = "$route/$itemId"
    }

    object Transactions : Route("transactions")
    object Settings : Route("settings")

    object ItemDetail : Route("itemDetail") {
        const val itemIdArg = "productId"
        val routeWithArgs: String
            get() = "$route/{$itemIdArg}"

        fun createRoute(itemId: String): String = "$route/$itemId"
    }
}