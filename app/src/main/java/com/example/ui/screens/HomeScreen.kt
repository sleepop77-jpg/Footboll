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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Player
import com.example.model.PositionCategory
import com.example.ui.components.LiveMatchTickerCard
import com.example.ui.components.PlayerAvatar
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel

@Composable
fun HomeScreen(
    viewModel: FootballViewModel,
    onPlayerClick: (String) -> Unit,
    onNavigateToLive: () -> Unit,
    onNavigateToDirectory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val liveMatches by viewModel.liveMatches.collectAsState()
    val prominentPlayers = viewModel.prominentPlayers
    val lastNotification by viewModel.lastEventNotification.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen_lazy_column"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // 1. Hero Broadcast Stadium Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .testTag("home_hero_banner")
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_stadium),
                    contentDescription = "Stadium Arena",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // High-contrast gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xFF070B14).copy(alpha = 0.75f),
                                    Color(0xFF070B14)
                                )
                            )
                        )
                )

                // Hero Content
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
                            color = NeonCyan.copy(alpha = 0.2f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(NeonCyan)
                                )
                                Text(
                                    text = "GLOBAL FOOTBALL HUB",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = NeonCyan,
                                    letterSpacing = 1.sp
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = GoldenTrophy.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "🏆 REAL-TIME MATCH ENGINE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldenTrophy,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "PitchPulse Global XI",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Deep performance analytics, live fixtures & complete star biographies",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Live Event Toast / Notification Banner if active
        item {
            AnimatedVisibility(visible = lastNotification != null) {
                lastNotification?.let { notif ->
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        tonalElevation = 6.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                            .clickable { onNavigateToLive() }
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
                                Text(text = "🚨", fontSize = 16.sp)
                                Column {
                                    Text(
                                        text = "LIVE MATCH ALERT • ${notif.minute}'",
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

        // 2. Prominent Football Icons Carousel (Highlighted Section)
        item {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = GoldenTrophy,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Highlighted Icons",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "Prominent names with full career & personal records",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    TextButton(onClick = onNavigateToDirectory) {
                        Text(
                            text = "View All",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(prominentPlayers) { player ->
                        ProminentPlayerCard(
                            player = player,
                            onClick = { onPlayerClick(player.id) }
                        )
                    }
                }
            }
        }

        // 3. Featured Real-Time Live Match Card
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFEF476F))
                        )
                        Text(
                            text = "Live Match Center",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Instant Goal Simulation Button
                        FilledTonalButton(
                            onClick = { viewModel.triggerInstantGoal() },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp).testTag("simulate_goal_button")
                        ) {
                            Text("⚡ Sim Goal", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        TextButton(onClick = onNavigateToLive) {
                            Text("All Matches (${liveMatches.size})", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                liveMatches.firstOrNull()?.let { topMatch ->
                    LiveMatchTickerCard(
                        match = topMatch,
                        onPlayerClick = onPlayerClick
                    )
                }
            }
        }

        // 4. Ballon d'Or & Legend Cabinet Feature Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clickable { onNavigateToDirectory() }
                    .testTag("golden_ball_showcase")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_golden_ball),
                        contentDescription = "Golden Ball Trophy",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = GoldenTrophy.copy(alpha = 0.2f)
                        ) {
                            Text(
                                text = "TROPHY VAULT",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldenTrophy,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Historical Honors & Ballon d'Or Records",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Explore Messi's 8 Ballon d'Ors, Ronaldo's 900+ goals, and Rodri's 2024 triumph.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 5. Position Quick Category Explorer
        item {
            Column(modifier = Modifier.padding(top = 18.dp)) {
                Text(
                    text = "Explore by Tactical Position",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PositionCategoryChip(
                        title = "Forwards",
                        subtitle = "ST • LW • RW",
                        icon = "⚡",
                        onClick = {
                            viewModel.updateCategory(PositionCategory.FORWARD)
                            onNavigateToDirectory()
                        },
                        modifier = Modifier.weight(1f)
                    )
                    PositionCategoryChip(
                        title = "Midfielders",
                        subtitle = "CAM • CM • CDM",
                        icon = "🎯",
                        onClick = {
                            viewModel.updateCategory(PositionCategory.MIDFIELDER)
                            onNavigateToDirectory()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProminentPlayerCard(
    player: Player,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)),
        modifier = modifier
            .width(200.dp)
            .clickable(onClick = onClick)
            .testTag("prominent_card_${player.id}")
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Top Row: Avatar & Overall Rating Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                PlayerAvatar(player = player, size = 56.dp)

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = NeonCyan.copy(alpha = 0.2f),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Text(
                        text = "${player.overallRating}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NeonCyan,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Player Name and Club
            Text(
                text = player.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
            Text(
                text = "${player.club} • #${player.number}",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )

            // Prominent Badge Pill
            player.prominentBadge?.let { badge ->
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = GoldenTrophy.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "⭐ $badge",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldenTrophy,
                        maxLines = 1,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(8.dp))

            // Quick Stats Row (Goals, Market Value)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Goals", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = "${player.careerTotals.totalGoals}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }
                Column {
                    Text(text = "Assists", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = "${player.careerTotals.totalAssists}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "Value", fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(text = player.marketValueEur, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldPitch)
                }
            }
        }
    }
}

@Composable
private fun PositionCategoryChip(
    title: String,
    subtitle: String,
    icon: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = icon, fontSize = 20.sp)
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                Text(text = subtitle, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
