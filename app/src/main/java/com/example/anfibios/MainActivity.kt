package com.example.anfibios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anfibios.ui.theme.AnfibiosTheme
import com.example.anfibios.ui.theme.screens.AnfibiosViewModel
import com.example.anfibios.ui.theme.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnfibiosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppAnfibios()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppAnfibios() {
    val viewModel: AnfibiosViewModel = viewModel(factory = AnfibiosViewModel.Factory)
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Mundo Anfibio",
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.ui.graphics.Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        HomeScreen(
            anfibiosUiState = viewModel.anfibiosState,
            contentPadding = innerPadding
        )
    }
}