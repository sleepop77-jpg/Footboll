package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.PlayerRepository
import com.example.data.RealtimeMatchEngine
import com.example.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class SortOption(val title: String) {
    RATING("Overall Rating"),
    GOALS("Career Goals"),
    ASSISTS("Career Assists"),
    MARKET_VALUE("Market Value"),
    AGE("Age")
}

class FootballViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(PositionCategory.ALL)
    val selectedCategory: StateFlow<PositionCategory> = _selectedCategory.asStateFlow()

    private val _prominentOnly = MutableStateFlow(false)
    val prominentOnly: StateFlow<Boolean> = _prominentOnly.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.RATING)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    private val _favoritePlayerIds = MutableStateFlow<Set<String>>(setOf("messi", "ronaldo", "bellingham"))
    val favoritePlayerIds: StateFlow<Set<String>> = _favoritePlayerIds.asStateFlow()

    private val _selectedPlayer = MutableStateFlow<Player?>(PlayerRepository.getPlayerById("messi"))
    val selectedPlayer: StateFlow<Player?> = _selectedPlayer.asStateFlow()

    // Head-to-Head Comparison selection
    private val _comparePlayer1 = MutableStateFlow<Player>(PlayerRepository.getPlayerById("messi")!!)
    val comparePlayer1: StateFlow<Player> = _comparePlayer1.asStateFlow()

    private val _comparePlayer2 = MutableStateFlow<Player>(PlayerRepository.getPlayerById("ronaldo")!!)
    val comparePlayer2: StateFlow<Player> = _comparePlayer2.asStateFlow()

    // Real-time match engine flows
    val liveMatches: StateFlow<List<LiveMatch>> = RealtimeMatchEngine.matchesState
    val isLiveTickerActive: StateFlow<Boolean> = RealtimeMatchEngine.isLiveTickerActive
    val lastEventNotification: StateFlow<MatchEvent?> = RealtimeMatchEngine.lastEventNotification

    // Filtered and sorted players list
    val filteredPlayers: StateFlow<List<Player>> = combine(
        _searchQuery,
        _selectedCategory,
        _prominentOnly,
        _sortOption
    ) { query, category, prominent, sort ->
        val list = PlayerRepository.filterPlayers(query, category, prominent)
        when (sort) {
            SortOption.RATING -> list.sortedByDescending { it.overallRating }
            SortOption.GOALS -> list.sortedByDescending { it.careerTotals.totalGoals }
            SortOption.ASSISTS -> list.sortedByDescending { it.careerTotals.totalAssists }
            SortOption.MARKET_VALUE -> list.sortedByDescending { parseMarketValue(it.marketValueEur) }
            SortOption.AGE -> list.sortedBy { it.personalLife.age }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PlayerRepository.players)

    val prominentPlayers: List<Player> = PlayerRepository.getProminentPlayers()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateCategory(category: PositionCategory) {
        _selectedCategory.value = category
    }

    fun setProminentOnly(prominent: Boolean) {
        _prominentOnly.value = prominent
    }

    fun setSortOption(sort: SortOption) {
        _sortOption.value = sort
    }

    fun selectPlayer(playerId: String) {
        _selectedPlayer.value = PlayerRepository.getPlayerById(playerId)
    }

    fun selectComparePlayer1(playerId: String) {
        PlayerRepository.getPlayerById(playerId)?.let { _comparePlayer1.value = it }
    }

    fun selectComparePlayer2(playerId: String) {
        PlayerRepository.getPlayerById(playerId)?.let { _comparePlayer2.value = it }
    }

    fun toggleFavorite(playerId: String) {
        val current = _favoritePlayerIds.value.toMutableSet()
        if (current.contains(playerId)) {
            current.remove(playerId)
        } else {
            current.add(playerId)
        }
        _favoritePlayerIds.value = current
    }

    fun isFavorite(playerId: String): Boolean {
        return _favoritePlayerIds.value.contains(playerId)
    }

    fun toggleLiveTicker() {
        RealtimeMatchEngine.toggleSimulation()
    }

    fun triggerInstantGoal() {
        RealtimeMatchEngine.triggerInstantGoal()
    }

    fun dismissNotification() {
        RealtimeMatchEngine.clearNotification()
    }

    private fun parseMarketValue(valueStr: String): Long {
        // e.g. "€180M" or "€15M"
        val clean = valueStr.replace("€", "").replace("M", "").trim()
        return (clean.toDoubleOrNull() ?: 0.0 * 1_000_000).toLong()
    }
}
