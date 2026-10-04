package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.MatchStatus
import com.example.ui.components.LiveMatchTickerCard
import com.example.ui.theme.CrimsonAlert
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel

@Composable
fun LiveMatchesScreen(
    viewModel: FootballViewModel,
    onPlayerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val matches by viewModel.liveMatches.collectAsState()
    val isLiveActive by viewModel.isLiveTickerActive.collectAsState()
    val lastNotification by viewModel.lastEventNotification.collectAsState()

    var selectedCompetitionFilter by remember { mutableStateOf("ALL") }

    val competitions = listOf("ALL", "UEFA Champions League", "Premier League", "Major League Soccer", "Saudi Pro League")

    val filteredMatches = remember(matches, selectedCompetitionFilter) {
        if (selectedCompetitionFilter == "ALL") matches
        else matches.filter { it.competition.equals(selectedCompetitionFilter, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("live_matches_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Hero Header Card with Match Action Graphic
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_match_action),
                    contentDescription = "Live Match Action",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.65f))
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = CrimsonAlert.copy(alpha = 0.3f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(CrimsonAlert)
                                )
                                Text(
                                    text = if (isLiveActive) "REAL-TIME ENGINE ACTIVE" else "TICKER PAUSED",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = CrimsonAlert
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Global Live Match Center",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Live scores, instant goal alerts, VAR events & in-game star ratings",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Live Controls Strip (Play/Pause, Trigger Instant Goal)
        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Live Simulation Controls",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Updates every 3.5s with realistic actions",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilledTonalButton(
                            onClick = { viewModel.toggleLiveTicker() },
                            modifier = Modifier.height(36.dp)
                        ) {
                            Icon(
                                imageVector = if (isLiveActive) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (isLiveActive) "Pause" else "Resume", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { viewModel.triggerInstantGoal() },
                            colors = ButtonDefaults.buttonColors(containerColor = NeonCyan, contentColor = Color(0xFF00382E)),
                            modifier = Modifier.height(36.dp).testTag("trigger_goal_button")
                        ) {
                            Text("⚡ Instant Goal", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Realtime Event Banner Toast
        item {
            AnimatedVisibility(visible = lastNotification != null) {
                lastNotification?.let { notif ->
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        tonalElevation = 6.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "⚽", fontSize = 18.sp)
                                Column {
                                    Text(
                                        text = "LIVE EVENT • ${notif.minute}'",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NeonCyan
                                    )
                                    Text(
                                        text = notif.description,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                            IconButton(onClick = { viewModel.dismissNotification() }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Dismiss",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Competition Filter Tabs
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(competitions) { comp ->
                    val isSelected = selectedCompetitionFilter == comp
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCompetitionFilter = comp },
                        label = { Text(if (comp == "ALL") "All Leagues" else comp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NeonCyan.copy(alpha = 0.2f),
                            selectedLabelColor = NeonCyan
                        )
                    )
                }
            }
        }

        // Live Matches List
        items(filteredMatches, key = { it.id }) { match ->
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                LiveMatchTickerCard(
                    match = match,
                    onPlayerClick = onPlayerClick
                )
            }
        }
    }
}
