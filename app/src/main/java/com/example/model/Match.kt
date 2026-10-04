package com.example.model

enum class MatchStatus {
    LIVE,
    HALFTIME,
    FULLTIME,
    UPCOMING
}

enum class EventType {
    GOAL,
    PENALTY_GOAL,
    ASSIST,
    YELLOW_CARD,
    RED_CARD,
    SUBSTITUTION,
    VAR_DECISION,
    SHOT_WOODWORK
}

data class MatchEvent(
    val id: String,
    val minute: Int,
    val extraMinute: Int? = null,
    val type: EventType,
    val playerName: String,
    val assistingPlayer: String? = null,
    val teamName: String,
    val isHomeTeam: Boolean,
    val description: String
)

data class LivePlayerMatchStat(
    val playerId: String,
    val playerName: String,
    val teamName: String,
    val isHomeTeam: Boolean,
    val liveRating: Float,
    val goals: Int,
    val assists: Int,
    val shotsOnTarget: Int,
    val passesCompleted: Int,
    val passAccuracyPct: Int,
    val distanceCoveredKm: Float,
    val heatLevel: Float // 0.0 to 1.0
)

data class LiveMatch(
    val id: String,
    val competition: String,
    val round: String,
    val stadium: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val minute: Int,
    val status: MatchStatus,
    val homeColor: Long = 0xFF1E3A8A,
    val awayColor: Long = 0xFFDC2626,
    val homePossession: Int = 52,
    val awayPossession: Int = 48,
    val homeXg: Float = 1.84f,
    val awayXg: Float = 1.12f,
    val homeShots: Int = 14,
    val awayShots: Int = 9,
    val homeShotsOnTarget: Int = 6,
    val awayShotsOnTarget: Int = 4,
    val events: List<MatchEvent>,
    val featuredPlayers: List<LivePlayerMatchStat>
)
