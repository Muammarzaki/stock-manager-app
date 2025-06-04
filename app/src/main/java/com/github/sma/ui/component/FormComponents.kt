package com.github.sma.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.sma.ui.theme.StockmanagerappTheme

@Composable
fun FormExample(modifier: Modifier = Modifier) {
    Column {
        OutlinedTextField(
            onValueChange = {},
            shape = RoundedCornerShape(15),
            label = { Text("Nama") },
            value = "",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FormExamplePreview() {
    StockmanagerappTheme {
        FormExample()
    }
}