package com.github.sma.ui.screen

sealed class Route(val route: String) {
    object Dashboard : Route("dashboard")
    object StockList : Route("stockList")
    object AddItem : Route("addItem")
    object EditItem : Route("editItem")
    object Transactions : Route("transactions")
}