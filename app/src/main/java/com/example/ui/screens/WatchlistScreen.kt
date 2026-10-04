package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerRepository
import com.example.ui.theme.GoldenTrophy
import com.example.viewmodel.FootballViewModel

@Composable
fun WatchlistScreen(
    viewModel: FootballViewModel,
    onPlayerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteIds by viewModel.favoritePlayerIds.collectAsState()
    val allPlayers = PlayerRepository.players
    val favoritePlayers = remember(favoriteIds) {
        allPlayers.filter { favoriteIds.contains(it.id) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("watchlist_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = GoldenTrophy,
                    modifier = Modifier.size(22.dp)
                )
                Column {
                    Text(
                        text = "Bookmarked Superstars",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${favoritePlayers.size} tracked global players with quick alerts",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        if (favoritePlayers.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "⭐", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No players bookmarked yet",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Tap the heart icon on any player to add them to your live watchlist.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        } else {
            items(favoritePlayers, key = { it.id }) { player ->
                PlayerListCard(
                    player = player,
                    isFavorite = true,
                    onFavoriteToggle = { viewModel.toggleFavorite(player.id) },
                    onClick = { onPlayerClick(player.id) }
                )
            }
        }
    }
}
