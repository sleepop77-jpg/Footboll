package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
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
import com.example.model.CareerMilestone
import com.example.model.Player
import com.example.ui.components.PitchPositionVisualizer
import com.example.ui.components.PlayerAvatar
import com.example.ui.components.PlayerRadarChart
import com.example.ui.components.SeasonPerformanceChart
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel

enum class DetailTab(val label: String, val icon: String) {
    ATTRIBUTES("Radar & Role", "📊"),
    CAREER_STATS("Career & Seasons", "📈"),
    PERSONAL_LIFE("Personal Life", "👤"),
    TROPHIES("Trophies", "🏆"),
    MILESTONES("Milestones", "⏳")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailScreen(
    player: Player,
    viewModel: FootballViewModel,
    onBackClick: () -> Unit,
    onCompareClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteIds by viewModel.favoritePlayerIds.collectAsState()
    val isFavorite = favoriteIds.contains(player.id)
    var selectedTab by remember { mutableStateOf(DetailTab.ATTRIBUTES) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = player.displayName,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    // Compare button
                    IconButton(
                        onClick = { onCompareClick(player.id) },
                        modifier = Modifier.testTag("detail_compare_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.CompareArrows,
                            contentDescription = "Compare",
                            tint = NeonCyan
                        )
                    }

                    // Favorite button
                    IconButton(
                        onClick = { viewModel.toggleFavorite(player.id) },
                        modifier = Modifier.testTag("detail_favorite_button")
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFFF3366) else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("player_detail_lazy_column"),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // 1. Header Hero Profile Card
            item {
                PlayerHeaderCard(player = player)
            }

            // 2. Tab Navigation Selector Bar
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(DetailTab.values()) { tab ->
                        val isSelected = selectedTab == tab
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) NeonCyan.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = if (isSelected) CardDefaults.outlinedCardBorder() else null,
                            modifier = Modifier
                                .clickable { selectedTab = tab }
                                .testTag("tab_${tab.name}")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(text = tab.icon, fontSize = 14.sp)
                                Text(
                                    text = tab.label,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) NeonCyan else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // 3. Tab Contents
            when (selectedTab) {
                DetailTab.ATTRIBUTES -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Radar Chart
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Attributes Hexagon Radar",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Surface(
                                            color = NeonCyan.copy(alpha = 0.15f),
                                            shape = RoundedCornerShape(6.dp)
                                        ) {
                                            Text(
                                                text = "Overall ${player.overallRating}",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = NeonCyan,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }

                                    PlayerRadarChart(attributes = player.attributes)
                                }
                            }

                            // Pitch Heatmap & Tactical Visualizer
                            PitchPositionVisualizer(
                                position = player.position,
                                coordinates = player.pitchPosition
                            )

                            // Numerical Attribute Sliders Breakdown
                            AttributeSlidersCard(attributes = player.attributes)
                        }
                    }
                }

                DetailTab.CAREER_STATS -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Career Totals Grid
                            CareerTotalsSummaryCard(totals = player.careerTotals)

                            // Historical Seasons Bar & Line Chart
                            SeasonPerformanceChart(seasonHistory = player.seasonHistory)
                        }
                    }
                }

                DetailTab.PERSONAL_LIFE -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PersonalBioCard(personal = player.personalLife)
                            FamilyAndRootsCard(personal = player.personalLife)
                            PhilanthropyAndBusinessCard(personal = player.personalLife)
                            HobbiesAndTriviaCard(personal = player.personalLife)
                        }
                    }
                }

                DetailTab.TROPHIES -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            TrophyCabinetShowcase(trophies = player.trophies)
                        }
                    }
                }

                DetailTab.MILESTONES -> {
                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = "Career Defining Milestones",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            player.milestones.forEach { milestone ->
                                MilestoneCard(milestone = milestone)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PlayerHeaderCard(player: Player) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Large Avatar
                PlayerAvatar(player = player, size = 80.dp)

                Spacer(modifier = Modifier.width(16.dp))

                // Name, Club, Details
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${player.club} • #${player.number}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "${player.nationality} ${player.flagEmoji} • ${player.league}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    player.prominentBadge?.let { badge ->
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = GoldenTrophy.copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = "⭐ $badge",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldenTrophy,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(12.dp))

            // Quick Info Badges Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickBadge(label = "Overall", value = "${player.overallRating}", valueColor = NeonCyan)
                QuickBadge(label = "Form Rating", value = "${player.formRating} 🔥", valueColor = GoldenTrophy)
                QuickBadge(label = "Market Value", value = player.marketValueEur, valueColor = EmeraldPitch)
                QuickBadge(label = "Preferred Foot", value = player.preferredFoot, valueColor = MaterialTheme.colorScheme.onSurface)
                QuickBadge(label = "Contract", value = player.contractUntil.replace("June ", "").replace("December ", ""), valueColor = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun QuickBadge(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = valueColor)
        Text(text = label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun CareerTotalsSummaryCard(totals: com.example.model.CareerTotals) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "All-Time Professional Totals",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                MetricItem(label = "Matches", value = "${totals.totalAppearances}", highlight = false)
                MetricItem(label = "Goals", value = "${totals.totalGoals}", highlight = true)
                MetricItem(label = "Assists", value = "${totals.totalAssists}", highlight = true)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                MetricItem(label = "Minutes Played", value = "${totals.totalMinutes}'", highlight = false)
                MetricItem(label = "Pass Accuracy", value = "${totals.passAccuracyPct}%", highlight = false)
                MetricItem(label = "Shot Conversion", value = "${totals.shotConversionPct}%", highlight = false)
            }
        }
    }
}

@Composable
private fun MetricItem(label: String, value: String, highlight: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold,
            color = if (highlight) NeonCyan else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AttributeSlidersCard(attributes: com.example.model.PlayerAttributes) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Detailed Attribute Ratings",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            AttributeRow(label = "Pace (PAC)", value = attributes.pace)
            AttributeRow(label = "Shooting (SHO)", value = attributes.shooting)
            AttributeRow(label = "Passing (PAS)", value = attributes.passing)
            AttributeRow(label = "Dribbling (DRI)", value = attributes.dribbling)
            AttributeRow(label = "Defending (DEF)", value = attributes.defending)
            AttributeRow(label = "Physicality (PHY)", value = attributes.physical)
            AttributeRow(label = "Vision (VIS)", value = attributes.vision)
            AttributeRow(label = "Composure (COM)", value = attributes.composure)
        }
    }
}

@Composable
private fun AttributeRow(label: String, value: Int) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
            Text(text = "$value", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = if (value >= 85) NeonCyan else MaterialTheme.colorScheme.onSurface)
        }
        Spacer(modifier = Modifier.height(3.dp))
        LinearProgressIndicator(
            progress = { value.toFloat() / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = if (value >= 85) NeonCyan else GoldenTrophy,
            trackColor = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
private fun TrophyCabinetShowcase(trophies: com.example.model.TrophyCabinet) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Major Trophies & Cabinet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(14.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                TrophyIconCount(icon = "🥇", count = trophies.ballonDor, label = "Ballon d'Or")
                TrophyIconCount(icon = "🏆", count = trophies.championsLeague, label = "UCL Titles")
                TrophyIconCount(icon = "🌍", count = trophies.worldCup, label = "World Cup")
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                TrophyIconCount(icon = "🛡️", count = trophies.domesticLeagueTitles, label = "League Titles")
                TrophyIconCount(icon = "🥈", count = trophies.domesticCups, label = "Domestic Cups")
                TrophyIconCount(icon = "👟", count = trophies.goldenBoots, label = "Golden Boots")
            }

            if (trophies.notableHonors.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Individual Honors & Global Records",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldenTrophy
                )
                Spacer(modifier = Modifier.height(6.dp))
                trophies.notableHonors.forEach { honor ->
                    Row(
                        modifier = Modifier.padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "•", color = GoldenTrophy, fontSize = 14.sp)
                        Text(text = honor, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}

@Composable
private fun TrophyIconCount(icon: String, count: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 24.sp)
        Text(text = "$count", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = GoldenTrophy)
        Text(text = label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

// Personal Life Section Cards
@Composable
private fun PersonalBioCard(personal: com.example.model.PersonalLife) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Identity & Physical Profile",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(10.dp))

            BioInfoRow(label = "Legal Full Name", value = personal.legalFullName)
            BioInfoRow(label = "Date of Birth", value = "${personal.dateOfBirth} (${personal.age} years old)")
            BioInfoRow(label = "Birthplace", value = personal.birthplace)
            BioInfoRow(label = "Height & Weight", value = "${personal.heightCm} cm • ${personal.weightKg} kg")
            BioInfoRow(label = "Nicknames", value = personal.nicknames.joinToString(", "))
        }
    }
}

@Composable
private fun FamilyAndRootsCard(personal: com.example.model.PersonalLife) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Childhood, Roots & Family",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Early Life & Discoveries:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NeonCyan
            )
            Text(
                text = personal.earlyLifeAndRoots,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Family & Relationships:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NeonCyan
            )
            Text(
                text = personal.familyAndRelationships,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 17.sp
            )
        }
    }
}

@Composable
private fun PhilanthropyAndBusinessCard(personal: com.example.model.PersonalLife) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Philanthropy & Business Ventures",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Charities & Foundations:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = EmeraldPitch
            )
            Text(
                text = personal.philanthropyAndCauses,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Investments & Brand Endorsements:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = EmeraldPitch
            )
            Text(
                text = personal.businessAndInvestments,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Key Sponsors: " + personal.endorsementsAndSponsors.joinToString(", "),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = GoldenTrophy
            )
        }
    }
}

@Composable
private fun HobbiesAndTriviaCard(personal: com.example.model.PersonalLife) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Hobbies & Unique Trivia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Off-Pitch Passions:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = NeonCyan
            )
            personal.hobbiesAndPassions.forEach { hobby ->
                Text(
                    text = "• $hobby",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Did You Know? (Trivia):",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = GoldenTrophy
            )
            personal.funFactsAndTrivia.forEach { fact ->
                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "💡", fontSize = 12.sp)
                    Text(
                        text = fact,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun BioInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun MilestoneCard(milestone: CareerMilestone) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = when (milestone.iconType) {
                    "TROPHY" -> GoldenTrophy.copy(alpha = 0.2f)
                    "RECORD" -> NeonCyan.copy(alpha = 0.2f)
                    else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                },
                modifier = Modifier.size(36.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = when (milestone.iconType) {
                            "TROPHY" -> "🏆"
                            "RECORD" -> "🔥"
                            "TRANSFER" -> "✈️"
                            else -> "⭐"
                        },
                        fontSize = 16.sp
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = milestone.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = milestone.year,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = NeonCyan
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = milestone.description,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
