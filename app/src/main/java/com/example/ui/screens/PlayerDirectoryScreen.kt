package com.example.ui.screens

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Player
import com.example.model.PositionCategory
import com.example.ui.components.PlayerAvatar
import com.example.ui.theme.EmeraldPitch
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel
import com.example.viewmodel.SortOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDirectoryScreen(
    viewModel: FootballViewModel,
    onPlayerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val prominentOnly by viewModel.prominentOnly.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()
    val players by viewModel.filteredPlayers.collectAsState()
    val favoriteIds by viewModel.favoritePlayerIds.collectAsState()

    var showSortMenu by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("player_directory_screen")
    ) {
        // Search & Filter Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                placeholder = { Text("Search player, club, or country...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                    focusedBorderColor = NeonCyan,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("directory_search_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Filter Chips Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Prominent Star toggle chip
                item {
                    FilterChip(
                        selected = prominentOnly,
                        onClick = { viewModel.setProminentOnly(!prominentOnly) },
                        label = { Text("⭐ Prominent Icons") },
                        leadingIcon = if (prominentOnly) {
                            { Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        } else null,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = GoldenTrophy.copy(alpha = 0.2f),
                            selectedLabelColor = GoldenTrophy
                        )
                    )
                }

                // Categories
                items(PositionCategory.values()) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { viewModel.updateCategory(category) },
                        label = { Text(category.title) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = NeonCyan.copy(alpha = 0.2f),
                            selectedLabelColor = NeonCyan
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Results count & Sort Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${players.size} Global Superstars",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )

                Box {
                    TextButton(
                        onClick = { showSortMenu = true },
                        modifier = Modifier.testTag("sort_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sort",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Sort: ${sortOption.title}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    DropdownMenu(
                        expanded = showSortMenu,
                        onDismissRequest = { showSortMenu = false }
                    ) {
                        SortOption.values().forEach { opt ->
                            DropdownMenuItem(
                                text = { Text(opt.title) },
                                onClick = {
                                    viewModel.setSortOption(opt)
                                    showSortMenu = false
                                },
                                leadingIcon = if (sortOption == opt) {
                                    { Icon(Icons.Default.Check, contentDescription = null, tint = NeonCyan) }
                                } else null
                            )
                        }
                    }
                }
            }
        }

        // Players List
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(players, key = { it.id }) { player ->
                PlayerListCard(
                    player = player,
                    isFavorite = favoriteIds.contains(player.id),
                    onFavoriteToggle = { viewModel.toggleFavorite(player.id) },
                    onClick = { onPlayerClick(player.id) }
                )
            }
        }
    }
}

@Composable
fun PlayerListCard(
    player: Player,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (player.isProminent) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("player_list_card_${player.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Player Avatar with Flag & Star
            PlayerAvatar(player = player, size = 60.dp)

            Spacer(modifier = Modifier.width(12.dp))

            // Player Info (Name, Club, Position)
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = player.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )
                }

                Text(
                    text = "${player.club} • #${player.number} • ${player.position.label}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )

                if (player.prominentBadge != null) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "⭐ ${player.prominentBadge}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldenTrophy,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Stats row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚽ ${player.careerTotals.totalGoals} goals",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "🅰️ ${player.careerTotals.totalAssists} ast",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = player.marketValueEur,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPitch
                    )
                }
            }

            // Right side: Overall Rating Pill & Favorite Icon
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = NeonCyan.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = "${player.overallRating}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NeonCyan,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                IconButton(
                    onClick = onFavoriteToggle,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFFF3366) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
