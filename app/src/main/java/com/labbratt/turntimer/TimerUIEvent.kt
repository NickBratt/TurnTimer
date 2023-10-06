package com.labbratt.turntimer

sealed class TimerUIEvent {
    object TogglePlayerOneTimer : TimerUIEvent()
    object TogglePlayerTwoTimer : TimerUIEvent()
    object TogglePlayerThreeTimer : TimerUIEvent()
    object TogglePlayerFourTimer : TimerUIEvent()
    object ResetTimer : TimerUIEvent()
}
