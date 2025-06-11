package com.github.sma.ui.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.github.sma.ui.theme.StockmanagerappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionNavBar(modifier: Modifier = Modifier, title: String? = null, onBackClicked: () -> Unit) {
    TopAppBar(


        modifier = modifier,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        onBackClicked()
                    },
                    modifier = Modifier
                        .padding(start = 8.dp, end = 18.dp)
                        .size(32.dp)
                ) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Beranda",
                        fontSize = 12.sp,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        style = LocalTextStyle.current.merge(
                            TextStyle(
                                lineHeight = 2.5.em,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                ),
                                lineHeightStyle = LineHeightStyle(
                                    alignment = LineHeightStyle.Alignment.Top,
                                    trim = LineHeightStyle.Trim.Both
                                )
                            )
                        )
                    )
                    Text(
                        title ?: "Unknown",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_9",)
@Composable
private fun ListHeaderPreview() {
    StockmanagerappTheme {
        SectionNavBar(onBackClicked = { Log.d("Preview", "Back button clicked in preview") })
    }
}