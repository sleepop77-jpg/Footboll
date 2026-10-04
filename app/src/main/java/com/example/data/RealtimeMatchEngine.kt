package com.example.data

import com.example.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

object RealtimeMatchEngine {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private var simulationJob: Job? = null

    private val initialMatches = listOf(
        LiveMatch(
            id = "match_ucl_madrid_city",
            competition = "UEFA Champions League",
            round = "Quarter-Final • 2nd Leg",
            stadium = "Santiago Bernabéu, Madrid",
            homeTeam = "Real Madrid",
            awayTeam = "Manchester City",
            homeScore = 2,
            awayScore = 2,
            minute = 76,
            status = MatchStatus.LIVE,
            homeColor = 0xFF1E293B,
            awayColor = 0xFF0284C7,
            homePossession = 48,
            awayPossession = 52,
            homeXg = 1.95f,
            awayXg = 2.10f,
            homeShots = 13,
            awayShots = 15,
            homeShotsOnTarget = 6,
            awayShotsOnTarget = 7,
            events = listOf(
                MatchEvent("e1", 14, null, EventType.GOAL, "Erling Haaland", "Kevin De Bruyne", "Manchester City", false, "Header from deep cross into top corner"),
                MatchEvent("e2", 32, null, EventType.GOAL, "Vinícius Júnior", "Jude Bellingham", "Real Madrid", true, "Blistering left-wing burst and bottom corner finish"),
                MatchEvent("e3", 44, null, EventType.YELLOW_CARD, "Rodri", null, "Manchester City", false, "Tactical foul stopping counter-attack"),
                MatchEvent("e4", 58, null, EventType.GOAL, "Kylian Mbappé", "Luka Modrić", "Real Madrid", true, "Thunderous first-time strike inside the box"),
                MatchEvent("e5", 69, null, EventType.GOAL, "Kevin De Bruyne", "Rodri", "Manchester City", false, "Sensational 25-yard curling strike into top right corner")
            ),
            featuredPlayers = listOf(
                LivePlayerMatchStat("vinicius", "Vinícius Júnior", "Real Madrid", true, 8.7f, 1, 0, 3, 28, 85, 8.4f, 0.9f),
                LivePlayerMatchStat("mbappe", "Kylian Mbappé", "Real Madrid", true, 8.5f, 1, 0, 4, 22, 82, 8.1f, 0.88f),
                LivePlayerMatchStat("bellingham", "Jude Bellingham", "Real Madrid", true, 8.3f, 0, 1, 2, 45, 91, 10.2f, 0.85f),
                LivePlayerMatchStat("haaland", "Erling Haaland", "Manchester City", false, 8.4f, 1, 0, 4, 14, 78, 7.8f, 0.82f),
                LivePlayerMatchStat("debruyne", "Kevin De Bruyne", "Manchester City", false, 8.9f, 1, 1, 3, 56, 89, 9.4f, 0.92f),
                LivePlayerMatchStat("rodri", "Rodri", "Manchester City", false, 8.6f, 0, 1, 1, 72, 94, 9.8f, 0.88f)
            )
        ),

        LiveMatch(
            id = "match_el_clasico_barca_bayern",
            competition = "UEFA Champions League",
            round = "Group Stage • Matchday 5",
            stadium = "Estadi Olímpic Lluís Companys",
            homeTeam = "FC Barcelona",
            awayTeam = "FC Bayern Munich",
            homeScore = 3,
            awayScore = 1,
            minute = 64,
            status = MatchStatus.LIVE,
            homeColor = 0xFF1D4ED8,
            awayColor = 0xFFDC2626,
            homePossession = 58,
            awayPossession = 42,
            homeXg = 2.45f,
            awayXg = 1.30f,
            homeShots = 16,
            awayShots = 8,
            homeShotsOnTarget = 8,
            awayShotsOnTarget = 3,
            events = listOf(
                MatchEvent("e10", 8, null, EventType.GOAL, "Lamine Yamal", "Pedri", "FC Barcelona", true, "Curled from edge of penalty box"),
                MatchEvent("e11", 21, null, EventType.GOAL, "Harry Kane", "Musiala", "FC Bayern Munich", false, "Clinical low drive across the keeper"),
                MatchEvent("e12", 38, null, EventType.GOAL, "Robert Lewandowski", "Lamine Yamal", "FC Barcelona", true, "Near-post tap-in following Yamal's trivela"),
                MatchEvent("e13", 55, null, EventType.GOAL, "Raphinha", "Marc Casadó", "FC Barcelona", true, "Breakaway finish in transition")
            ),
            featuredPlayers = listOf(
                LivePlayerMatchStat("yamal", "Lamine Yamal", "FC Barcelona", true, 9.2f, 1, 1, 4, 41, 88, 7.6f, 0.95f),
                LivePlayerMatchStat("kane", "Harry Kane", "FC Bayern Munich", false, 7.8f, 1, 0, 2, 24, 80, 7.1f, 0.75f)
            )
        ),

        LiveMatch(
            id = "match_inter_miami",
            competition = "Major League Soccer",
            round = "Eastern Conference Final",
            stadium = "Chase Stadium, Fort Lauderdale",
            homeTeam = "Inter Miami CF",
            awayTeam = "Columbus Crew",
            homeScore = 2,
            awayScore = 0,
            minute = 82,
            status = MatchStatus.LIVE,
            homeColor = 0xFFEC4899,
            awayColor = 0xFFEAB308,
            homePossession = 61,
            awayPossession = 39,
            homeXg = 2.15f,
            awayXg = 0.65f,
            homeShots = 11,
            awayShots = 5,
            homeShotsOnTarget = 5,
            awayShotsOnTarget = 2,
            events = listOf(
                MatchEvent("e20", 34, null, EventType.GOAL, "Lionel Messi", "Jordi Alba", "Inter Miami CF", true, "Trademark chip over rushing goalkeeper"),
                MatchEvent("e21", 62, null, EventType.GOAL, "Lionel Messi", null, "Inter Miami CF", true, "Direct 28-yard free kick into top corner")
            ),
            featuredPlayers = listOf(
                LivePlayerMatchStat("messi", "Lionel Messi", "Inter Miami CF", true, 9.6f, 2, 0, 5, 52, 92, 7.9f, 0.98f)
            )
        ),

        LiveMatch(
            id = "match_al_nassr",
            competition = "Saudi Pro League",
            round = "Matchday 22",
            stadium = "Al-Awwal Park, Riyadh",
            homeTeam = "Al-Nassr FC",
            awayTeam = "Al-Hilal SFC",
            homeScore = 1,
            awayScore = 1,
            minute = 70,
            status = MatchStatus.LIVE,
            homeColor = 0xFFFACC15,
            awayColor = 0xFF1E40AF,
            homePossession = 49,
            awayPossession = 51,
            homeXg = 1.40f,
            awayXg = 1.55f,
            homeShots = 10,
            awayShots = 12,
            homeShotsOnTarget = 4,
            awayShotsOnTarget = 5,
            events = listOf(
                MatchEvent("e30", 25, null, EventType.GOAL, "Cristiano Ronaldo", "Otávio", "Al-Nassr FC", true, "Bullet header at the back post"),
                MatchEvent("e31", 53, null, EventType.GOAL, "Aleksandar Mitrović", "Rúben Neves", "Al-Hilal SFC", false, "Equalizer from rebound")
            ),
            featuredPlayers = listOf(
                LivePlayerMatchStat("ronaldo", "Cristiano Ronaldo", "Al-Nassr FC", true, 8.4f, 1, 0, 5, 20, 83, 7.3f, 0.88f)
            )
        )
    )

    private val _matchesState = MutableStateFlow(initialMatches)
    val matchesState: StateFlow<List<LiveMatch>> = _matchesState.asStateFlow()

    private val _isLiveTickerActive = MutableStateFlow(true)
    val isLiveTickerActive: StateFlow<Boolean> = _isLiveTickerActive.asStateFlow()

    private val _lastEventNotification = MutableStateFlow<MatchEvent?>(null)
    val lastEventNotification: StateFlow<MatchEvent?> = _lastEventNotification.asStateFlow()

    init {
        startSimulation()
    }

    fun startSimulation() {
        if (simulationJob?.isActive == true) return
        _isLiveTickerActive.value = true

        simulationJob = scope.launch {
            while (isActive) {
                delay(3500) // Live pulse interval
                advanceMatchSimulation()
            }
        }
    }

    fun stopSimulation() {
        simulationJob?.cancel()
        simulationJob = null
        _isLiveTickerActive.value = false
    }

    fun toggleSimulation() {
        if (_isLiveTickerActive.value) {
            stopSimulation()
        } else {
            startSimulation()
        }
    }

    private fun advanceMatchSimulation() {
        val currentList = _matchesState.value
        val updated = currentList.map { match ->
            if (match.status != MatchStatus.LIVE) return@map match

            var newMinute = match.minute + 1
            var newStatus = match.status

            if (newMinute >= 90) {
                newMinute = 90
                newStatus = MatchStatus.FULLTIME
            }

            // Chance of a live match event
            val eventRoll = (1..100).random()
            var newHomeScore = match.homeScore
            var newAwayScore = match.awayScore
            var newEvents = match.events.toMutableList()
            var newHomeXg = match.homeXg
            var newAwayXg = match.awayXg

            if (eventRoll in 1..22 && newStatus == MatchStatus.LIVE) {
                // Generate dynamic realistic event
                val isHome = (1..2).random() == 1
                val eventTypeRoll = (1..10).random()

                val createdEvent: MatchEvent = when {
                    eventTypeRoll in 1..4 -> {
                        // Goal!
                        if (isHome) {
                            newHomeScore++
                            newHomeXg += 0.45f
                        } else {
                            newAwayScore++
                            newAwayXg += 0.45f
                        }
                        val scorer = if (isHome) {
                            match.featuredPlayers.find { it.isHomeTeam }?.playerName ?: "${match.homeTeam} Striker"
                        } else {
                            match.featuredPlayers.find { !it.isHomeTeam }?.playerName ?: "${match.awayTeam} Attacker"
                        }
                        MatchEvent(
                            id = UUID.randomUUID().toString(),
                            minute = newMinute,
                            type = EventType.GOAL,
                            playerName = scorer,
                            teamName = if (isHome) match.homeTeam else match.awayTeam,
                            isHomeTeam = isHome,
                            description = "GOAL! Magnificent finish into the corner by $scorer!"
                        )
                    }
                    eventTypeRoll in 5..7 -> {
                        val player = if (isHome) "${match.homeTeam} Defender" else "${match.awayTeam} Midfielder"
                        MatchEvent(
                            id = UUID.randomUUID().toString(),
                            minute = newMinute,
                            type = EventType.YELLOW_CARD,
                            playerName = player,
                            teamName = if (isHome) match.homeTeam else match.awayTeam,
                            isHomeTeam = isHome,
                            description = "Yellow card awarded for reckless sliding tackle"
                        )
                    }
                    eventTypeRoll == 8 -> {
                        MatchEvent(
                            id = UUID.randomUUID().toString(),
                            minute = newMinute,
                            type = EventType.VAR_DECISION,
                            playerName = "VAR Referee",
                            teamName = match.homeTeam,
                            isHomeTeam = isHome,
                            description = "VAR Check: Potential penalty reviewed and overturned by referee"
                        )
                    }
                    else -> {
                        MatchEvent(
                            id = UUID.randomUUID().toString(),
                            minute = newMinute,
                            type = EventType.SHOT_WOODWORK,
                            playerName = if (isHome) "${match.homeTeam} Forward" else "${match.awayTeam} Forward",
                            teamName = if (isHome) match.homeTeam else match.awayTeam,
                            isHomeTeam = isHome,
                            description = "Off the crossbar! Tremendous thunderbolt denies a goal"
                        )
                    }
                }

                newEvents.add(0, createdEvent) // prepend newest
                _lastEventNotification.value = createdEvent
            }

            match.copy(
                minute = newMinute,
                status = newStatus,
                homeScore = newHomeScore,
                awayScore = newAwayScore,
                homeXg = ((newHomeXg * 100).toInt()) / 100f,
                awayXg = ((newAwayXg * 100).toInt()) / 100f,
                events = newEvents
            )
        }
        _matchesState.value = updated
    }

    /**
     * User can manually trigger an immediate goal event on demand!
     */
    fun triggerInstantGoal() {
        val currentList = _matchesState.value.toMutableList()
        if (currentList.isEmpty()) return
        val target = currentList[0]
        val newEvent = MatchEvent(
            id = UUID.randomUUID().toString(),
            minute = target.minute + 1,
            type = EventType.GOAL,
            playerName = target.featuredPlayers.firstOrNull()?.playerName ?: "Star Player",
            teamName = target.homeTeam,
            isHomeTeam = true,
            description = "STUNNING GOAL! Rocket strike into top bins from 30 yards out!"
        )
        val updatedTarget = target.copy(
            homeScore = target.homeScore + 1,
            minute = target.minute + 1,
            homeXg = target.homeXg + 0.52f,
            events = listOf(newEvent) + target.events
        )
        currentList[0] = updatedTarget
        _matchesState.value = currentList
        _lastEventNotification.value = newEvent
    }

    fun clearNotification() {
        _lastEventNotification.value = null
    }
}
