package com.example.parmegianocounter.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import com.example.parmegianocounter.ui.vm.DishViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.getViewModel

class WordListenerService() : Service() {

    var onParmegianoDetected: () -> Unit = {}

    private val words = listOf("parmeggiano", "parmigiano", "parmegiano", "parmetiano", "parmeciano", "karmeliano", "farmęgiano", "parmetiano", "farmeriano", "parmezan")

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): WordListenerService = this@WordListenerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        activateSpeechRecognizer(this)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun activateSpeechRecognizer(
        context: Context,
    ) {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) { return }

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        val recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {`
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
        }

        speechRecognizer.startListening(recognizerIntent)

        val recognitionListener = object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {
                if(error == 7){
                    speechRecognizer.stopListening()
                    speechRecognizer.startListening(recognizerIntent)
                }
                Log.v("ERROR", "ERROR $error")
            }

            override fun onResults(results: Bundle?) {
                val data: ArrayList<String>? =
                    results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                Log.d("SpeechRecognizer", "Speech recognition results received: $data")
                if (data != null) {
                    detectWord(data, onParmegianoDetected)
                }
                speechRecognizer.stopListening()
                speechRecognizer.startListening(recognizerIntent)
            }

            override fun onPartialResults(partialResults: Bundle?) {
                val data: ArrayList<String>? =
                    partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                Log.d("Partial", "Partial peech recognition results received: $data")
            }

            override fun onEvent(eventType: Int, params: Bundle?) {}
        }

        speechRecognizer.setRecognitionListener(recognitionListener)

    }

    fun detectWord(
        input: ArrayList<String>,
        onParmegianoDetected: () -> Unit
    ){
        val count = input.map{it.lowercase()}.count { it in words || it.endsWith("giano") || it.endsWith("ciano") || it.startsWith("parme") || it.startsWith("parmi")}
        if(count > 0){
            onParmegianoDetected()
        }
    }
}