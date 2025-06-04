package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.sma.ui.component.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengaturan") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Pengaturan Aplikasi", style = MaterialTheme.typography.headlineSmall)
            SettingItem(
                title = "Notifikasi Stok Rendah",
                description = "Aktifkan untuk menerima notifikasi",
                checked = true,
                onCheckedChange = {})
            SettingItem(
                title = "Mode Gelap",
                description = "Sesuaikan tema aplikasi",
                checked = false,
                onCheckedChange = {})
            Divider()
            TextButton(onClick = { /*TODO: Export data*/ }) {
                Text("Ekspor Data Stok")
            }
            TextButton(onClick = { /*TODO: Import data*/ }) {
                Text("Impor Data Stok")
            }
        }
    }
}