package com.example.parmegianocounter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.parmegianocounter.ui.DishCard
import com.example.parmegianocounter.ui.theme.ParmegianoCounterTheme
import com.example.parmegianocounter.ui.vm.DishViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel = getViewModel<DishViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            val k = uiState.dishes.collectAsState(emptyList()).value

            ParmegianoCounterTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(Modifier.fillMaxSize()) {

                        items(k.size) {
                            DishCard(k[it].name, k[it].additional, k[it].price.toString())
                        }
                    }
                }
            }
        }
    }
}