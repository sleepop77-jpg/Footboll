package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PitchCoordinates
import com.example.model.PlayerPosition
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan

@Composable
fun PitchPositionVisualizer(
    position: PlayerPosition,
    coordinates: PitchCoordinates,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "beacon_transition")
    val pulseRadius by infiniteTransition.animateFloat(
        initialValue = 8f,
        targetValue = 24f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_radius"
    )
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse_alpha"
    )

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        modifier = modifier.testTag("pitch_visualizer")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Tactical Role & Pitch Heatmap",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${position.label} (${position.name})",
                        fontSize = 12.sp,
                        color = NeonCyan,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Surface(
                    color = EmeraldPitch.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Attack Direction ➔",
                        fontSize = 10.sp,
                        color = EmeraldPitch,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // The Pitch Canvas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF0F2B1D),
                                Color(0xFF133E2A),
                                Color(0xFF0F2B1D)
                            )
                        )
                    )
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height
                    val lineColor = Color.White.copy(alpha = 0.28f)
                    val stroke = Stroke(width = 1.5.dp.toPx())

                    // Outer pitch boundary
                    drawRoundRect(
                        color = lineColor,
                        topLeft = Offset(8.dp.toPx(), 8.dp.toPx()),
                        size = Size(w - 16.dp.toPx(), h - 16.dp.toPx()),
                        cornerRadius = CornerRadius(4.dp.toPx()),
                        style = stroke
                    )

                    // Halfway Line
                    drawLine(
                        color = lineColor,
                        start = Offset(w / 2f, 8.dp.toPx()),
                        end = Offset(w / 2f, h - 8.dp.toPx()),
                        strokeWidth = 1.5.dp.toPx()
                    )

                    // Center Circle
                    drawCircle(
                        color = lineColor,
                        radius = 28.dp.toPx(),
                        center = Offset(w / 2f, h / 2f),
                        style = stroke
                    )
                    drawCircle(
                        color = lineColor,
                        radius = 2.dp.toPx(),
                        center = Offset(w / 2f, h / 2f)
                    )

                    // Left Penalty Box (Defensive Goal)
                    val boxW = w * 0.18f
                    val boxH = h * 0.58f
                    val boxTop = (h - boxH) / 2f
                    drawRect(
                        color = lineColor,
                        topLeft = Offset(8.dp.toPx(), boxTop),
                        size = Size(boxW, boxH),
                        style = stroke
                    )

                    // Right Penalty Box (Attacking Goal)
                    drawRect(
                        color = lineColor,
                        topLeft = Offset(w - 8.dp.toPx() - boxW, boxTop),
                        size = Size(boxW, boxH),
                        style = stroke
                    )

                    // Heatmap Aura around player coordinate
                    val playerX = 8.dp.toPx() + (coordinates.xPct * (w - 16.dp.toPx()))
                    val playerY = 8.dp.toPx() + (coordinates.yPct * (h - 16.dp.toPx()))

                    // Outer heat glow
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFFFF3366).copy(alpha = 0.45f),
                                Color(0xFFFF9900).copy(alpha = 0.25f),
                                Color.Transparent
                            ),
                            center = Offset(playerX, playerY),
                            radius = 45.dp.toPx()
                        ),
                        radius = 45.dp.toPx(),
                        center = Offset(playerX, playerY)
                    )

                    // Pulsing Beacon
                    drawCircle(
                        color = NeonCyan.copy(alpha = pulseAlpha),
                        radius = pulseRadius.dp.toPx(),
                        center = Offset(playerX, playerY),
                        style = Stroke(width = 2.dp.toPx())
                    )

                    // Player Node Pin
                    drawCircle(
                        color = Color.White,
                        radius = 7.dp.toPx(),
                        center = Offset(playerX, playerY)
                    )
                    drawCircle(
                        color = NeonCyan,
                        radius = 5.dp.toPx(),
                        center = Offset(playerX, playerY)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Primary Zone Description & Secondary Influences
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Primary Zone: ", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = coordinates.primaryZoneName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }
                if (coordinates.secondaryZones.isNotEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Active Channels: ", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(text = coordinates.secondaryZones.joinToString(", "), fontSize = 11.sp, color = GoldenTrophy)
                    }
                }
            }
        }
    }
}
