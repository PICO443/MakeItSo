package com.pico.make_it_so.presentation.home.focus_session.time_picker.session

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pico.make_it_so.R
import com.pico.make_it_so.presentation._nav_graphs.FocusSessionNavGraph
import com.pico.make_it_so.ui.theme.MakeItSoTheme
import com.ramcosta.composedestinations.annotation.Destination
import kotlin.math.*


@FocusSessionNavGraph(start = true)
@Destination()
@Composable
fun FocusSessionScreen(
    viewModel: FocusSessionViewModel = hiltViewModel(),
    sessionTimeMinutes: Int = 0
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(FocusSessionEvent.StartSession(sessionTimeMinutes))
    }


    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "${uiState.sessionState.label} ${uiState.sessionState.num} of ${uiState.sessionState.totalNum}",
            )
            CustomCircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                label = "${uiState.remainingTime.millisToMinutes()}",
                percentage = uiState.remainingTimePercentage,
                primaryColor = MaterialTheme.colorScheme.primary,
                secondaryColor = MaterialTheme.colorScheme.surface,
                circleRadius = 150f,
                onPositionChange = { position ->
                    // Do something with the new position
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                FilledIconButton(onClick = { viewModel.onEvent(FocusSessionEvent.TogglePause) }) {
                    Icon(
                        painter = painterResource(R.drawable.pause_fill1_wght400_grad0_opsz24),
                        contentDescription = null
                    )
                }
                OutlinedIconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.stop_fill0_wght400_grad0_opsz24),
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    label: String,
    percentage: Int,
    primaryColor: Color,
    secondaryColor: Color,
    minValue: Int = 0,
    maxValue: Int = 100,
    circleRadius: Float,
    onPositionChange: (Int) -> Unit
) {
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
    var positionValue by remember { mutableStateOf(percentage) }
//    var positionValue = percentage()
    var changeAngle by remember { mutableStateOf(0f) }
    var dragStartedAngle by remember { mutableStateOf(0f) }
    var oldPositionValue by remember { mutableStateOf(percentage) }
//    var oldPositionValue = percentage()

    LaunchedEffect(key1 = percentage) {
        positionValue = percentage
    }
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            dragStartedAngle = -atan2(
                                x = circleCenter.y - offset.y,
                                y = circleCenter.x - offset.x
                            ) * (180f / PI).toFloat()
                            dragStartedAngle = (dragStartedAngle + 180f).mod(360f)
                        },
                        onDrag = { change, _ ->
                            var touchAngle = -atan2(
                                x = circleCenter.y - change.position.y,
                                y = circleCenter.x - change.position.x
                            ) * (180f / PI).toFloat()
                            touchAngle = (touchAngle + 180f).mod(360f)

                            val currentAngle = oldPositionValue * 360f / (maxValue - minValue)
                            changeAngle = touchAngle - currentAngle

                            val lowerThreshold = currentAngle - (360f / (maxValue - minValue) * 5)
                            val higherThreshold = currentAngle + (360f / (maxValue - minValue) * 5)

                            if (dragStartedAngle in lowerThreshold..higherThreshold) {
                                positionValue =
                                    (oldPositionValue + (changeAngle / (360f / (maxValue - minValue))).roundToInt())
                            }
                        },
                        onDragEnd = {
                            oldPositionValue = positionValue
                            onPositionChange(positionValue)
                        }
                    )
                }
        ) {
            val width = size.width
            val height = size.height
            val circleThickness = width / 25f
            circleCenter = Offset(x = width / 2f, y = height / 2f)
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(0.45f),
                        secondaryColor.copy(0.15f)
                    )
                ),
                radius = circleRadius,
                center = circleCenter
            )
            drawCircle(
                style = Stroke(
                    width = circleThickness
                ),
                color = secondaryColor,
                radius = circleRadius,
                center = circleCenter
            )
            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle = (360f / maxValue) * positionValue.toFloat(),
                style = Stroke(
                    width = circleThickness,
                    cap = StrokeCap.Butt
                ),
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f) / 2f,
                    (height - circleRadius * 2f) / 2f
                )
            )

            val outerRadius = circleRadius + circleThickness / 2f
            val gap = 15f

            for (i in 0..(maxValue - minValue)) {
                val color =
                    if (i < positionValue - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                val angleInDegrees = i * 360f / (maxValue - minValue).toFloat()
                val angleInRad = angleInDegrees * PI / 180f + PI / 2f
                val yGapAdjustment = cos(angleInDegrees * PI / 180f) * gap
                val xGapAdjustment = -sin(angleInDegrees * PI / 180f) * gap
                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
                )
                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
                )

                rotate(
                    angleInDegrees,
                    pivot = start
                ) {
                    drawLine(
                        color = color,
                        start = start,
                        end = end,
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$label",
                        circleCenter.x,
                        circleCenter.y + 45.dp.toPx() / 3f,
                        Paint().apply {
                            textSize = 38.sp.toPx()
                            textAlign = Paint.Align.CENTER
                            color = Color.White.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FocusSessionPrev() {
    MakeItSoTheme {
        FocusSessionScreen(sessionTimeMinutes = 50)
    }
}