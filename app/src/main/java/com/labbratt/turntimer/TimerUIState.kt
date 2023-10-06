package com.labbratt.turntimer

data class TimerUIState(
    val playerCount: Int = 0,
    val isPlayerOneTimerGoing: Boolean = false,
    val isPlayerTwoTimerGoing: Boolean = false,
    val isPlayerThreeTimerGoing: Boolean = false,
    val isPlayerFourTimerGoing: Boolean = false,
    var playerOneCurrentTime: Long = 0,
    var playerTwoCurrentTime: Long = 0,
    var playerThreeCurrentTime: Long = 0,
    var playerFourCurrentTime: Long = 0,
)
