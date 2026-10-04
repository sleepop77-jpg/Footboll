package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PlayerAttributes
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun PlayerRadarChart(
    attributes: PlayerAttributes,
    modifier: Modifier = Modifier,
    comparisonAttributes: PlayerAttributes? = null,
    primaryColor: Color = NeonCyan,
    comparisonColor: Color = GoldenTrophy,
    primaryLabel: String = "Player",
    comparisonLabel: String? = null
) {
    val labels = listOf("PAC", "SHO", "PAS", "DRI", "DEF", "PHY", "VIS", "COM")
    val rawValues = listOf(
        attributes.pace,
        attributes.shooting,
        attributes.passing,
        attributes.dribbling,
        attributes.defending,
        attributes.physical,
        attributes.vision,
        attributes.composure
    )

    val compRawValues = comparisonAttributes?.let {
        listOf(it.pace, it.shooting, it.passing, it.dribbling, it.defending, it.physical, it.vision, it.composure)
    }

    // Animate transition on load
    var isStarted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isStarted = true }
    val progress by animateFloatAsState(
        targetValue = if (isStarted) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "radar_anim"
    )

    Column(
        modifier = modifier.testTag("radar_chart_container"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize().testTag("radar_chart_canvas")) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = (minOf(size.width, size.height) / 2f) * 0.72f
                val count = labels.size
                val angleStep = (2 * PI / count).toFloat()

                // Draw Web Polygons (Grid Rings)
                val rings = listOf(0.25f, 0.50f, 0.75f, 1.0f)
                rings.forEach { ringFrac ->
                    val ringPath = Path()
                    for (i in 0 until count) {
                        val angle = (i * angleStep) - (PI / 2).toFloat()
                        val x = center.x + radius * ringFrac * cos(angle)
                        val y = center.y + radius * ringFrac * sin(angle)
                        if (i == 0) ringPath.moveTo(x, y) else ringPath.lineTo(x, y)
                    }
                    ringPath.close()
                    drawPath(
                        path = ringPath,
                        color = Color.White.copy(alpha = if (ringFrac == 1f) 0.25f else 0.10f),
                        style = Stroke(width = if (ringFrac == 1f) 1.5f else 1f)
                    )
                }

                // Draw Spoke Lines from center to outer ring
                for (i in 0 until count) {
                    val angle = (i * angleStep) - (PI / 2).toFloat()
                    val endX = center.x + radius * cos(angle)
                    val endY = center.y + radius * sin(angle)
                    drawLine(
                        color = Color.White.copy(alpha = 0.15f),
                        start = center,
                        end = Offset(endX, endY),
                        strokeWidth = 1f
                    )
                }

                // Draw Comparison Polygon if available
                if (compRawValues != null) {
                    val compPath = Path()
                    for (i in 0 until count) {
                        val angle = (i * angleStep) - (PI / 2).toFloat()
                        val statFraction = (compRawValues[i].coerceIn(0, 100) / 100f) * progress
                        val x = center.x + radius * statFraction * cos(angle)
                        val y = center.y + radius * statFraction * sin(angle)
                        if (i == 0) compPath.moveTo(x, y) else compPath.lineTo(x, y)
                    }
                    compPath.close()

                    drawPath(
                        path = compPath,
                        color = comparisonColor.copy(alpha = 0.30f),
                        style = Fill
                    )
                    drawPath(
                        path = compPath,
                        color = comparisonColor,
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                // Draw Primary Polygon
                val primaryPath = Path()
                for (i in 0 until count) {
                    val angle = (i * angleStep) - (PI / 2).toFloat()
                    val statFraction = (rawValues[i].coerceIn(0, 100) / 100f) * progress
                    val x = center.x + radius * statFraction * cos(angle)
                    val y = center.y + radius * statFraction * sin(angle)
                    if (i == 0) primaryPath.moveTo(x, y) else primaryPath.lineTo(x, y)
                }
                primaryPath.close()

                drawPath(
                    path = primaryPath,
                    color = primaryColor.copy(alpha = 0.40f),
                    style = Fill
                )
                drawPath(
                    path = primaryPath,
                    color = primaryColor,
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )

                // Draw Corner Stat Nodes
                for (i in 0 until count) {
                    val angle = (i * angleStep) - (PI / 2).toFloat()
                    val statFraction = (rawValues[i].coerceIn(0, 100) / 100f) * progress
                    val x = center.x + radius * statFraction * cos(angle)
                    val y = center.y + radius * statFraction * sin(angle)
                    drawCircle(color = Color.White, radius = 4.dp.toPx(), center = Offset(x, y))
                    drawCircle(color = primaryColor, radius = 2.5.dp.toPx(), center = Offset(x, y))
                }
            }
        }

        // Stat Label Values Grid Below
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.take(4).forEachIndexed { idx, label ->
                StatBadge(label = label, value = rawValues[idx], color = primaryColor)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.drop(4).forEachIndexed { idx, label ->
                StatBadge(label = label, value = rawValues[idx + 4], color = primaryColor)
            }
        }
    }
}

@Composable
private fun StatBadge(label: String, value: Int, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "$value",
            fontSize = 15.sp,
            color = color,
            fontWeight = FontWeight.Bold
        )
    }
}
