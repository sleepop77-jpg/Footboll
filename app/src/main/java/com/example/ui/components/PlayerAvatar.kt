package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Player
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan

@Composable
fun PlayerAvatar(
    player: Player,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    showBadges: Boolean = true
) {
    val context = LocalContext.current
    val borderBrush = if (player.isProminent) {
        Brush.sweepGradient(listOf(GoldenTrophy, NeonCyan, GoldenTrophy))
    } else {
        Brush.linearGradient(listOf(Color.White.copy(alpha = 0.3f), Color.White.copy(alpha = 0.1f)))
    }

    Box(
        modifier = modifier
            .size(size)
            .testTag("player_avatar_${player.id}"),
        contentAlignment = Alignment.Center
    ) {
        // Outer glowing halo for prominent superstars
        if (player.isProminent) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(GoldenTrophy.copy(alpha = 0.2f))
            )
        }

        // Main Image / Graphic
        Box(
            modifier = Modifier
                .size(size - if (player.isProminent) 6.dp else 4.dp)
                .clip(CircleShape)
                .border(
                    width = if (player.isProminent) 2.5.dp else 1.5.dp,
                    brush = borderBrush,
                    shape = CircleShape
                )
                .background(Color(player.clubBadgeColor).copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center
        ) {
            if (player.photoUrl.isNotBlank()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(player.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = player.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Fallback monogram & icon
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = player.name,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(size / 2)
                    )
                }
            }
        }

        // Nationality Flag & Jersey Pill Overlays
        if (showBadges) {
            // Flag Pill on bottom-end
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 3.dp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp)
            ) {
                Text(
                    text = player.flagEmoji,
                    fontSize = (size.value * 0.22f).sp,
                    modifier = Modifier.padding(1.dp)
                )
            }

            // Star indicator for prominent legends on top-start
            if (player.isProminent) {
                Surface(
                    shape = CircleShape,
                    color = GoldenTrophy,
                    shadowElevation = 3.dp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = (-2).dp, y = (-2).dp)
                        .size((size.value * 0.32f).coerceAtLeast(18f).dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Prominent Superstar",
                            tint = Color.Black,
                            modifier = Modifier.fillMaxSize(0.7f)
                        )
                    }
                }
            }
        }
    }
}
