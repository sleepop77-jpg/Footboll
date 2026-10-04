package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerRepository
import com.example.model.Player
import com.example.ui.components.PlayerAvatar
import com.example.ui.components.PlayerRadarChart
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel

@Composable
fun ComparisonScreen(
    viewModel: FootballViewModel,
    modifier: Modifier = Modifier
) {
    val player1 by viewModel.comparePlayer1.collectAsState()
    val player2 by viewModel.comparePlayer2.collectAsState()
    val allPlayers = PlayerRepository.players

    var showPicker1 by remember { mutableStateOf(false) }
    var showPicker2 by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("comparison_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Player Selection Row
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Player 1 Selector
                    Box(modifier = Modifier.weight(1f)) {
                        PlayerSelectBadge(
                            player = player1,
                            accentColor = NeonCyan,
                            onClick = { showPicker1 = true }
                        )
                        DropdownMenu(
                            expanded = showPicker1,
                            onDismissRequest = { showPicker1 = false }
                        ) {
                            allPlayers.forEach { p ->
                                DropdownMenuItem(
                                    text = { Text("${p.flagEmoji} ${p.name} (${p.club})") },
                                    onClick = {
                                        viewModel.selectComparePlayer1(p.id)
                                        showPicker1 = false
                                    }
                                )
                            }
                        }
                    }

                    // VS Pill in middle
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 4.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "VS",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                        )
                    }

                    // Player 2 Selector
                    Box(modifier = Modifier.weight(1f)) {
                        PlayerSelectBadge(
                            player = player2,
                            accentColor = GoldenTrophy,
                            onClick = { showPicker2 = true }
                        )
                        DropdownMenu(
                            expanded = showPicker2,
                            onDismissRequest = { showPicker2 = false }
                        ) {
                            allPlayers.forEach { p ->
                                DropdownMenuItem(
                                    text = { Text("${p.flagEmoji} ${p.name} (${p.club})") },
                                    onClick = {
                                        viewModel.selectComparePlayer2(p.id)
                                        showPicker2 = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Dual Overlaid Radar Chart
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Attributes Comparison Radar",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LegendItem(name = player1.name, color = NeonCyan)
                        Spacer(modifier = Modifier.width(20.dp))
                        LegendItem(name = player2.name, color = GoldenTrophy)
                    }

                    PlayerRadarChart(
                        attributes = player1.attributes,
                        comparisonAttributes = player2.attributes,
                        primaryColor = NeonCyan,
                        comparisonColor = GoldenTrophy,
                        primaryLabel = player1.name,
                        comparisonLabel = player2.name
                    )
                }
            }
        }

        // Side-by-Side Performance Comparison Table
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Head-to-Head Key Metrics",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    CompareMetricRow(
                        label = "Overall Rating",
                        val1 = "${player1.overallRating}",
                        val2 = "${player2.overallRating}",
                        num1 = player1.overallRating.toFloat(),
                        num2 = player2.overallRating.toFloat()
                    )
                    CompareMetricRow(
                        label = "Career Goals",
                        val1 = "${player1.careerTotals.totalGoals}",
                        val2 = "${player2.careerTotals.totalGoals}",
                        num1 = player1.careerTotals.totalGoals.toFloat(),
                        num2 = player2.careerTotals.totalGoals.toFloat()
                    )
                    CompareMetricRow(
                        label = "Career Assists",
                        val1 = "${player1.careerTotals.totalAssists}",
                        val2 = "${player2.careerTotals.totalAssists}",
                        num1 = player1.careerTotals.totalAssists.toFloat(),
                        num2 = player2.careerTotals.totalAssists.toFloat()
                    )
                    CompareMetricRow(
                        label = "Ballon d'Ors",
                        val1 = "${player1.trophies.ballonDor}",
                        val2 = "${player2.trophies.ballonDor}",
                        num1 = player1.trophies.ballonDor.toFloat(),
                        num2 = player2.trophies.ballonDor.toFloat()
                    )
                    CompareMetricRow(
                        label = "Champions Leagues",
                        val1 = "${player1.trophies.championsLeague}",
                        val2 = "${player2.trophies.championsLeague}",
                        num1 = player1.trophies.championsLeague.toFloat(),
                        num2 = player2.trophies.championsLeague.toFloat()
                    )
                    CompareMetricRow(
                        label = "World Cups",
                        val1 = "${player1.trophies.worldCup}",
                        val2 = "${player2.trophies.worldCup}",
                        num1 = player1.trophies.worldCup.toFloat(),
                        num2 = player2.trophies.worldCup.toFloat()
                    )
                    CompareMetricRow(
                        label = "Pass Accuracy",
                        val1 = "${player1.careerTotals.passAccuracyPct}%",
                        val2 = "${player2.careerTotals.passAccuracyPct}%",
                        num1 = player1.careerTotals.passAccuracyPct,
                        num2 = player2.careerTotals.passAccuracyPct
                    )
                    CompareMetricRow(
                        label = "Shot Conversion",
                        val1 = "${player1.careerTotals.shotConversionPct}%",
                        val2 = "${player2.careerTotals.shotConversionPct}%",
                        num1 = player1.careerTotals.shotConversionPct,
                        num2 = player2.careerTotals.shotConversionPct
                    )
                    CompareMetricRow(
                        label = "Duels Won",
                        val1 = "${player1.careerTotals.duelsWonPct}%",
                        val2 = "${player2.careerTotals.duelsWonPct}%",
                        num1 = player1.careerTotals.duelsWonPct,
                        num2 = player2.careerTotals.duelsWonPct
                    )
                }
            }
        }

        // Profile & Physical Comparison Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Physical & Profile Comparison",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileMetricRow(label = "Age", v1 = "${player1.personalLife.age} yrs", v2 = "${player2.personalLife.age} yrs")
                    ProfileMetricRow(label = "Height", v1 = "${player1.personalLife.heightCm} cm", v2 = "${player2.personalLife.heightCm} cm")
                    ProfileMetricRow(label = "Weight", v1 = "${player1.personalLife.weightKg} kg", v2 = "${player2.personalLife.weightKg} kg")
                    ProfileMetricRow(label = "Foot", v1 = player1.preferredFoot, v2 = player2.preferredFoot)
                    ProfileMetricRow(label = "Market Value", v1 = player1.marketValueEur, v2 = player2.marketValueEur)
                    ProfileMetricRow(label = "Club", v1 = player1.club, v2 = player2.club)
                }
            }
        }
    }
}

@Composable
private fun PlayerSelectBadge(
    player: Player,
    accentColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(6.dp)
    ) {
        PlayerAvatar(player = player, size = 56.dp)
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = player.displayName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                maxLines = 1
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = "${player.club} • OVR ${player.overallRating}",
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun LegendItem(name: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = name,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CompareMetricRow(
    label: String,
    val1: String,
    val2: String,
    num1: Float,
    num2: Float
) {
    val win1 = num1 > num2
    val win2 = num2 > num1

    Column(modifier = Modifier.padding(vertical = 5.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = val1,
                fontSize = 13.sp,
                fontWeight = if (win1) FontWeight.ExtraBold else FontWeight.Medium,
                color = if (win1) NeonCyan else MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = label,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = val2,
                fontSize = 13.sp,
                fontWeight = if (win2) FontWeight.ExtraBold else FontWeight.Medium,
                color = if (win2) GoldenTrophy else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Differential bar
        val total = (num1 + num2).coerceAtLeast(0.1f)
        val frac1 = (num1 / total).coerceIn(0.05f, 0.95f)
        val frac2 = 1.0f - frac1

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
        ) {
            Box(
                modifier = Modifier
                    .weight(frac1)
                    .fillMaxHeight()
                    .background(if (win1) NeonCyan else NeonCyan.copy(alpha = 0.35f))
            )
            Spacer(modifier = Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .weight(frac2)
                    .fillMaxHeight()
                    .background(if (win2) GoldenTrophy else GoldenTrophy.copy(alpha = 0.35f))
            )
        }
    }
}

@Composable
private fun ProfileMetricRow(label: String, v1: String, v2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = v1, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = NeonCyan)
        Text(text = label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = v2, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = GoldenTrophy)
    }
}
