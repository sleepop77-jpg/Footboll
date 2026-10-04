package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SeasonRecord
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan

@Composable
fun SeasonPerformanceChart(
    seasonHistory: List<SeasonRecord>,
    modifier: Modifier = Modifier
) {
    if (seasonHistory.isEmpty()) return

    var selectedSeasonIndex by remember { mutableIntStateOf(0) }
    var viewMode by remember { mutableStateOf("GOALS_ASSISTS") } // or "RATING"

    val maxGoals = (seasonHistory.maxOfOrNull { it.goals } ?: 40).coerceAtLeast(20)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f))
            .padding(16.dp)
            .testTag("season_performance_chart")
    ) {
        // Header with interactive toggles
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Historical Seasons",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(2.dp)
            ) {
                TabPill(
                    title = "Goals / Ast",
                    selected = viewMode == "GOALS_ASSISTS",
                    onClick = { viewMode = "GOALS_ASSISTS" }
                )
                TabPill(
                    title = "Match Rating",
                    selected = viewMode == "RATING",
                    onClick = { viewMode = "RATING" }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bars visualization
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            seasonHistory.forEachIndexed { index, record ->
                val isSelected = index == selectedSeasonIndex

                val barTarget = if (viewMode == "GOALS_ASSISTS") {
                    record.goals.toFloat() / maxGoals.toFloat()
                } else {
                    (record.averageRating - 6.0f).coerceAtLeast(0.1f) / 3.5f
                }

                val animatedHeightFraction by animateFloatAsState(
                    targetValue = barTarget.coerceIn(0.1f, 1.0f),
                    animationSpec = tween(durationMillis = 600),
                    label = "bar_height"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedSeasonIndex = index }
                        .padding(horizontal = 3.dp)
                        .testTag("season_bar_$index")
                ) {
                    // Stat text over bar
                    Text(
                        text = if (viewMode == "GOALS_ASSISTS") "${record.goals}" else String.format("%.1f", record.averageRating),
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) NeonCyan else MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // The Bar itself
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(animatedHeightFraction)
                            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                            .background(
                                if (isSelected) {
                                    Brush.verticalGradient(listOf(NeonCyan, EmeraldPitch))
                                } else {
                                    Brush.verticalGradient(
                                        listOf(
                                            GoldenTrophy.copy(alpha = 0.6f),
                                            GoldenTrophy.copy(alpha = 0.2f)
                                        )
                                    )
                                }
                            )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Season label
                    Text(
                        text = record.season.replace("20", ""),
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Selected Season Detail Card
        val currentRecord = seasonHistory.getOrNull(selectedSeasonIndex) ?: seasonHistory.first()
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${currentRecord.season} • ${currentRecord.club}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = currentRecord.competition,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Surface(
                        color = NeonCyan.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Rating: ${String.format("%.2f", currentRecord.averageRating)}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    QuickStat(label = "Matches", value = "${currentRecord.appearances}")
                    QuickStat(label = "Goals", value = "${currentRecord.goals}")
                    QuickStat(label = "Assists", value = "${currentRecord.assists}")
                    QuickStat(
                        label = "G+A / Match",
                        value = String.format("%.2f", (currentRecord.goals + currentRecord.assists).toFloat() / currentRecord.appearances.coerceAtLeast(1))
                    )
                }

                if (currentRecord.honors.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "🏆", fontSize = 13.sp)
                        Text(
                            text = currentRecord.honors.joinToString(", "),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = GoldenTrophy
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
        Text(text = label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun TabPill(title: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
