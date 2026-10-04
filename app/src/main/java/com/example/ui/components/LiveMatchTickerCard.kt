package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import com.example.model.EventType
import com.example.model.LiveMatch
import com.example.model.MatchStatus
import com.example.ui.theme.CrimsonAlert
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan

@Composable
fun LiveMatchTickerCard(
    match: LiveMatch,
    modifier: Modifier = Modifier,
    onPlayerClick: ((String) -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse_live")
    val liveAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "live_alpha"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .testTag("match_card_${match.id}")
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Header Row: Competition, Round & Live Minute Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = match.competition,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${match.round} • ${match.stadium}",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Match Status Pill
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = when (match.status) {
                        MatchStatus.LIVE -> CrimsonAlert.copy(alpha = 0.2f)
                        MatchStatus.HALFTIME -> GoldenTrophy.copy(alpha = 0.2f)
                        MatchStatus.FULLTIME -> EmeraldPitch.copy(alpha = 0.2f)
                        MatchStatus.UPCOMING -> MaterialTheme.colorScheme.surface
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (match.status == MatchStatus.LIVE) {
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(CrimsonAlert.copy(alpha = liveAlpha))
                            )
                            Text(
                                text = "${match.minute}' LIVE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = CrimsonAlert
                            )
                        } else {
                            Text(
                                text = match.status.name,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Scoreboard Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Team
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = match.homeTeam,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "xG ${String.format("%.2f", match.homeXg)}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Score Center Box
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 4.dp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${match.homeScore}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = NeonCyan
                        )
                        Text(
                            text = ":",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${match.awayScore}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = NeonCyan
                        )
                    }
                }

                // Away Team
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = match.awayTeam,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "xG ${String.format("%.2f", match.awayXg)}",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Dual Possession Bar
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${match.homePossession}% Poss",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "${match.awayPossession}% Poss",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .weight(match.homePossession.toFloat().coerceAtLeast(1f))
                            .fillMaxHeight()
                            .background(NeonCyan)
                    )
                    Box(
                        modifier = Modifier
                            .weight(match.awayPossession.toFloat().coerceAtLeast(1f))
                            .fillMaxHeight()
                            .background(GoldenTrophy)
                    )
                }
            }

            // Latest Goal Event Quick Preview
            val latestGoal = match.events.firstOrNull { it.type == EventType.GOAL || it.type == EventType.PENALTY_GOAL }
            if (latestGoal != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = "⚽", fontSize = 12.sp)
                    Text(
                        text = "${latestGoal.minute}' ${latestGoal.playerName} (${latestGoal.teamName})",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Expand / Collapse Chevron indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (expanded) "Hide Live Match Timeline" else "View Live Timeline & Player Ratings",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }

            // Expanded Match Timeline & Featured Player In-Game Ratings
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Featured Superstars In-Game Performance",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    // Player rating cards
                    match.featuredPlayers.forEach { pStat ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { onPlayerClick?.invoke(pStat.playerId) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = if (pStat.liveRating >= 8.5f) NeonCyan.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Text(
                                            text = String.format("%.1f", pStat.liveRating),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = if (pStat.liveRating >= 8.5f) NeonCyan else MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = pStat.playerName,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "${pStat.teamName} • ${pStat.shotsOnTarget} SOT • ${pStat.passAccuracyPct}% pass",
                                            fontSize = 10.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    if (pStat.goals > 0) {
                                        Text(text = "⚽ ${pStat.goals}", fontSize = 11.sp)
                                    }
                                    if (pStat.assists > 0) {
                                        Text(text = "🅰️ ${pStat.assists}", fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Live Match Events Feed",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    match.events.take(6).forEach { ev ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(vertical = 3.dp)
                        ) {
                            Text(
                                text = "${ev.minute}'",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = NeonCyan
                            )
                            val iconStr = when (ev.type) {
                                EventType.GOAL, EventType.PENALTY_GOAL -> "⚽"
                                EventType.YELLOW_CARD -> "🟨"
                                EventType.RED_CARD -> "🟥"
                                EventType.VAR_DECISION -> "📺"
                                EventType.SUBSTITUTION -> "🔄"
                                EventType.SHOT_WOODWORK -> "🪵"
                                else -> "⚡"
                            }
                            Text(text = iconStr, fontSize = 12.sp)
                            Text(
                                text = "${ev.playerName}: ${ev.description}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
