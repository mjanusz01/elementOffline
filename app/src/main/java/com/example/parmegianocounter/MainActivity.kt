package com.example.parmegianocounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.parmegianocounter.ui.components.ConnectionCard
import com.example.parmegianocounter.ui.components.DishCard
import com.example.parmegianocounter.ui.theme.ParmegianoCounterTheme
import com.example.parmegianocounter.ui.vm.DishViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel = getViewModel<DishViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val dishes = uiState.dishes.collectAsState(emptyList()).value

            ParmegianoCounterTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ConnectionCard(connectionState = uiState.connectionState, modifier = Modifier.weight(0.2F))
                    LazyColumn(
                        Modifier
                            .fillMaxSize().weight(0.8F)) {
                        items(dishes.size) {
                            DishCard(dishes[it].name, dishes[it].additional, dishes[it].price.toString())
                        }
                    }
                }
            }
        }
    }
}