package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PlayerRepository
import com.example.ui.screens.*
import com.example.ui.theme.CrimsonAlert
import com.example.ui.theme.FootyPulseTheme
import com.example.ui.theme.GoldenTrophy
import com.example.ui.theme.NeonCyan
import com.example.viewmodel.FootballViewModel

enum class NavDestination(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
    DIRECTORY("directory", "Players", Icons.Filled.SportsSoccer, Icons.Outlined.SportsSoccer),
    LIVE("live", "Live Hub", Icons.Filled.Sensors, Icons.Outlined.Sensors),
    COMPARE("compare", "Compare", Icons.Filled.CompareArrows, Icons.Outlined.CompareArrows),
    WATCHLIST("watchlist", "Watchlist", Icons.Filled.Star, Icons.Outlined.StarOutline)
}

class MainActivity : ComponentActivity() {

    private val viewModel: FootballViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FootyPulseTheme(darkTheme = true) {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(viewModel: FootballViewModel) {
    var currentDestination by remember { mutableStateOf(NavDestination.HOME) }
    var viewingPlayerId by remember { mutableStateOf<String?>(null) }

    val isLiveActive by viewModel.isLiveTickerActive.collectAsState()

    // Pulse animation for LIVE icon
    val infiniteTransition = rememberInfiniteTransition(label = "nav_live_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "nav_pulse_alpha"
    )

    // Handle system back button when viewing player details
    BackHandler(enabled = viewingPlayerId != null) {
        viewingPlayerId = null
    }

    if (viewingPlayerId != null) {
        val player = PlayerRepository.getPlayerById(viewingPlayerId!!)
        if (player != null) {
            PlayerDetailScreen(
                player = player,
                viewModel = viewModel,
                onBackClick = { viewingPlayerId = null },
                onCompareClick = { pid ->
                    viewModel.selectComparePlayer1(pid)
                    viewingPlayerId = null
                    currentDestination = NavDestination.COMPARE
                }
            )
            return
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "⚡ FootyPulse",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = NeonCyan
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                text = "GLOBAL XI",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = GoldenTrophy,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                },
                actions = {
                    // Live match ticker status
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = CrimsonAlert.copy(alpha = 0.2f),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(CrimsonAlert.copy(alpha = pulseAlpha))
                            )
                            Text(
                                text = "MATCH ENGINE",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = CrimsonAlert
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .testTag("main_navigation_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavDestination.values().forEach { destination ->
                    val isSelected = currentDestination == destination
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentDestination = destination },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (destination == NavDestination.LIVE) {
                                        Badge(
                                            containerColor = CrimsonAlert,
                                            modifier = Modifier.size(6.dp)
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = destination.title
                                )
                            }
                        },
                        label = {
                            Text(
                                text = destination.title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = NeonCyan,
                            selectedTextColor = NeonCyan,
                            indicatorColor = NeonCyan.copy(alpha = 0.15f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag("nav_${destination.route}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentDestination) {
                NavDestination.HOME -> {
                    HomeScreen(
                        viewModel = viewModel,
                        onPlayerClick = { playerId -> viewingPlayerId = playerId },
                        onNavigateToLive = { currentDestination = NavDestination.LIVE },
                        onNavigateToDirectory = { currentDestination = NavDestination.DIRECTORY }
                    )
                }

                NavDestination.DIRECTORY -> {
                    PlayerDirectoryScreen(
                        viewModel = viewModel,
                        onPlayerClick = { playerId -> viewingPlayerId = playerId }
                    )
                }

                NavDestination.LIVE -> {
                    LiveMatchesScreen(
                        viewModel = viewModel,
                        onPlayerClick = { playerId -> viewingPlayerId = playerId }
                    )
                }

                NavDestination.COMPARE -> {
                    ComparisonScreen(viewModel = viewModel)
                }

                NavDestination.WATCHLIST -> {
                    WatchlistScreen(
                        viewModel = viewModel,
                        onPlayerClick = { playerId -> viewingPlayerId = playerId }
                    )
                }
            }
        }
    }
}
