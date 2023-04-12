package com.pico.make_it_so.presentation._nav_graphs

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class OnBoardingNavGraph(val start: Boolean = false)
