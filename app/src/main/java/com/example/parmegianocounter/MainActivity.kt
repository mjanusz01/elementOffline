package com.example.parmegianocounter

import android.os.Bundle
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

    /*private lateinit var mService: WordListenerService
    private var mBound: Boolean = false
    var onParmegianoMentioned = {}

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as WordListenerService.LocalBinder
            mService = binder.getService()
            mBound = true
            mService.onParmegianoDetected = onParmegianoMentioned
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel = getViewModel<DishViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            //onParmegianoMentioned = { //viewModel.onParmegianoMentioned() }

            /*SignalReceiver(
                onMentioned = { //viewModel.onParmegianoMentioned() }
            }
            )

            Intent(applicationContext, WordListenerService::class.java).also {
                startService(it)
            }*/

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

    /*override fun onStart() {
        super.onStart()
        // Bind to LocalService.
        Intent(this, WordListenerService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }*/
}