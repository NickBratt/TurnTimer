package com.labbratt.turntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.labbratt.turntimer.TimeFormatter.formatTime
import com.labbratt.turntimer.ui.theme.DarkGrey
import com.labbratt.turntimer.ui.theme.TurnTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurnTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    SetStatusBarColor(color = DarkGrey)
                    TimerScreen()
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterPlayerScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(DarkGrey),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Enter Number of Players: ",
            color = Color.White,
            modifier = modifier.padding(bottom = 12.dp),
        )
        var playerCount by remember { mutableStateOf("") }
        TextField(
            value = playerCount,
            onValueChange = { playerCount = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = DarkGrey,
                focusedIndicatorColor = Color.White,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                IconButton(
                    onClick = { /* TODO changePlayerCount(playerCount.toInt()) */ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Enter",
                        tint = Color.White,
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerScreen(
    timerViewModel: TimerViewModel = viewModel()
) {
    val state = timerViewModel.uiState.collectAsState()
    val haptics = LocalHapticFeedback.current
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGrey),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f)
                        .padding(0.dp)
                        .border(BorderStroke(2.dp, Color.White))
                        .clickable { timerViewModel.onEvent(TimerUIEvent.TogglePlayerOneTimer) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = state.value.playerOneCurrentTime.formatTime(),
                        color = Color.White,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .border(BorderStroke(2.dp, Color.White))
                        .clickable { timerViewModel.onEvent(TimerUIEvent.TogglePlayerTwoTimer) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.playerTwoCurrentTime.formatTime(),
                        color = Color.White,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f)
                        .border(BorderStroke(2.dp, Color.White))
                        .clickable { timerViewModel.onEvent(TimerUIEvent.TogglePlayerThreeTimer) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.playerThreeCurrentTime.formatTime(),
                        color = Color.White,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .border(BorderStroke(2.dp, Color.White))
                        .clickable { timerViewModel.onEvent(TimerUIEvent.TogglePlayerFourTimer) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.value.playerFourCurrentTime.formatTime(),
                        color = Color.White,
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
                .combinedClickable(
                    onClick = {},
                    onLongClick = {
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        timerViewModel.onEvent(TimerUIEvent.ResetTimer)
                    }
                )
                .clip(CircleShape)
                .background(Color.White),
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Reset button",
                tint = Color.Black,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TurnTimerTheme {
        TimerScreen()
    }
}