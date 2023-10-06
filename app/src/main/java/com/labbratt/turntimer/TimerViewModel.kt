package com.labbratt.turntimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(TimerUIState())
    val uiState: StateFlow<TimerUIState> = _uiState

    fun onEvent(event: TimerUIEvent) {
        when (event) {
            is TimerUIEvent.TogglePlayerOneTimer -> {
                _uiState.value = _uiState.value.copy(
                    isPlayerOneTimerGoing = !_uiState.value.isPlayerOneTimerGoing,
                    isPlayerTwoTimerGoing = false,
                    isPlayerThreeTimerGoing = false,
                    isPlayerFourTimerGoing = false
                )
                viewModelScope.launch {
                    while (_uiState.value.isPlayerOneTimerGoing) {
                        delay(100L)
                        _uiState.value = _uiState.value.copy(
                            playerOneCurrentTime = _uiState.value.playerOneCurrentTime + 100L
                        )
                    }
                }
            }

            is TimerUIEvent.TogglePlayerTwoTimer -> {
                _uiState.value = _uiState.value.copy(
                    isPlayerOneTimerGoing = false,
                    isPlayerTwoTimerGoing = !_uiState.value.isPlayerTwoTimerGoing,
                    isPlayerThreeTimerGoing = false,
                    isPlayerFourTimerGoing = false
                )
                viewModelScope.launch {
                    while (_uiState.value.isPlayerTwoTimerGoing) {
                        delay(100L)
                        _uiState.value =
                            _uiState.value.copy(
                                playerTwoCurrentTime = _uiState.value.playerTwoCurrentTime + 100L
                            )
                    }
                }
            }

            is TimerUIEvent.TogglePlayerThreeTimer -> {
                _uiState.value = _uiState.value.copy(
                    isPlayerOneTimerGoing = false,
                    isPlayerTwoTimerGoing = false,
                    isPlayerThreeTimerGoing = !_uiState.value.isPlayerThreeTimerGoing,
                    isPlayerFourTimerGoing = false
                )
                viewModelScope.launch {
                    while (_uiState.value.isPlayerThreeTimerGoing) {
                        delay(100L)
                        _uiState.value =
                            _uiState.value.copy(
                                playerThreeCurrentTime = _uiState.value.playerThreeCurrentTime + 100L
                            )
                    }
                }
            }

            is TimerUIEvent.TogglePlayerFourTimer -> {
                _uiState.value = _uiState.value.copy(
                    isPlayerOneTimerGoing = false,
                    isPlayerTwoTimerGoing = false,
                    isPlayerThreeTimerGoing = false,
                    isPlayerFourTimerGoing = !_uiState.value.isPlayerFourTimerGoing
                )
                viewModelScope.launch {
                    while (_uiState.value.isPlayerFourTimerGoing) {
                        delay(100L)
                        _uiState.value =
                            _uiState.value.copy(
                                playerFourCurrentTime = _uiState.value.playerFourCurrentTime + 100L
                            )
                    }
                }
            }

            is TimerUIEvent.ResetTimer -> {
                _uiState.value = _uiState.value.copy(
                    isPlayerOneTimerGoing = false,
                    isPlayerTwoTimerGoing = false,
                    isPlayerThreeTimerGoing = false,
                    isPlayerFourTimerGoing = false,
                    playerOneCurrentTime = 0,
                    playerTwoCurrentTime = 0,
                    playerThreeCurrentTime = 0,
                    playerFourCurrentTime = 0,
                )
            }
        }
    }
}