package com.pico.make_it_so.presentation.home.focus_session

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.pico.make_it_so.presentation._nav_graphs.HomeNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@HomeNavGraph
@Destination(style = FocusSessionTransition::class)
@Composable
fun FocusSessionScreen() {
    Text(text = "Focus")
}