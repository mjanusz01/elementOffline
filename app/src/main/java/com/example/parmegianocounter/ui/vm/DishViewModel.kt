package com.example.parmegianocounter.ui.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parmegianocounter.data.model.Dish
import com.example.parmegianocounter.data.model.asEntity
import com.example.parmegianocounter.data.repository.DishRepository
import com.example.parmegianocounter.data.repository.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DishViewModel(
    private val dishRepository: DishRepository
) : ViewModel() {

    init {
        val service = Executors.newSingleThreadScheduledExecutor()
        val handler = Handler(Looper.getMainLooper())
        service.scheduleWithFixedDelay({
            handler.run {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        when (val result = dishRepository.downloadDishes()) {
                            is NetworkResult.Success -> {
                                dishRepository.deleteDishes()
                                dishRepository.upsertDishes(result.data.dishes.map { it.asEntity() })
                            }

                            else -> {

                            }
                        }
                    }
                }
            }
        }, 0, 15, TimeUnit.SECONDS);
    }

    private val dishStream: Flow<List<Dish>> = dishRepository.getDishes().catch { }

    private val _uiState = MutableStateFlow(CounterUiState(dishes = dishStream))
    val uiState: StateFlow<CounterUiState> = _uiState

}

data class CounterUiState(
    val count: Int = 0,
    val isRecognitionActive: Boolean = false,
    val dishes: Flow<List<Dish>>
)

