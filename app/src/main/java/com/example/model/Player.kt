package com.example.model

/**
 * Detailed representation of a global football player spanning
 * full professional performance analytics and in-depth personal biography.
 */
data class Player(
    val id: String,
    val name: String,
    val displayName: String,
    val number: Int,
    val position: PlayerPosition,
    val club: String,
    val clubBadgeColor: Long,
    val league: String,
    val nationality: String,
    val nationalityCode: String, // 2-letter ISO or flag symbol
    val flagEmoji: String,
    val isProminent: Boolean = false,
    val prominentBadge: String? = null, // e.g. "Ballon d'Or Legend", "Golden Boy", "Top Scorer"
    val localDrawableRes: Int? = null,
    val photoUrl: String,
    val overallRating: Int, // e.g. 93
    val formRating: Float, // e.g. 8.85
    val marketValueEur: String, // e.g. "€180M"
    val contractUntil: String, // e.g. "June 2029"
    val preferredFoot: String, // "Left" or "Right" or "Both"

    // Professional Performance
    val attributes: PlayerAttributes,
    val careerTotals: CareerTotals,
    val seasonHistory: List<SeasonRecord>,
    val trophies: TrophyCabinet,
    val milestones: List<CareerMilestone>,
    val pitchPosition: PitchCoordinates,

    // Personal Life & Deep Biography
    val personalLife: PersonalLife
)

enum class PlayerPosition(val label: String, val category: PositionCategory) {
    GK("Goalkeeper", PositionCategory.GOALKEEPER),
    CB("Center-Back", PositionCategory.DEFENDER),
    LB("Left-Back", PositionCategory.DEFENDER),
    RB("Right-Back", PositionCategory.DEFENDER),
    CDM("Defensive Midfielder", PositionCategory.MIDFIELDER),
    CM("Central Midfielder", PositionCategory.MIDFIELDER),
    CAM("Attacking Midfielder", PositionCategory.MIDFIELDER),
    LW("Left Winger", PositionCategory.FORWARD),
    RW("Right Winger", PositionCategory.FORWARD),
    ST("Striker / Center-Forward", PositionCategory.FORWARD)
}

enum class PositionCategory(val title: String) {
    ALL("All Positions"),
    FORWARD("Forwards"),
    MIDFIELDER("Midfielders"),
    DEFENDER("Defenders"),
    GOALKEEPER("Goalkeepers")
}

data class PlayerAttributes(
    val pace: Int,
    val shooting: Int,
    val passing: Int,
    val dribbling: Int,
    val defending: Int,
    val physical: Int,
    val vision: Int = 85,
    val composure: Int = 90
)

data class CareerTotals(
    val totalAppearances: Int,
    val totalGoals: Int,
    val totalAssists: Int,
    val totalMinutes: Int,
    val passAccuracyPct: Float,
    val shotConversionPct: Float,
    val duelsWonPct: Float,
    val cleanSheets: Int? = null
)

data class SeasonRecord(
    val season: String, // e.g. "2024/25"
    val club: String,
    val competition: String,
    val appearances: Int,
    val goals: Int,
    val assists: Int,
    val averageRating: Float,
    val honors: List<String> = emptyList()
)

data class TrophyCabinet(
    val ballonDor: Int,
    val championsLeague: Int,
    val worldCup: Int,
    val continentalCup: Int, // e.g. Copa America, Euro, AFCON
    val domesticLeagueTitles: Int,
    val domesticCups: Int,
    val goldenBoots: Int,
    val notableHonors: List<String>
)

data class CareerMilestone(
    val year: String,
    val title: String,
    val description: String,
    val iconType: String = "TROPHY" // "DEBUT", "RECORD", "TROPHY", "TRANSFER"
)

data class PitchCoordinates(
    val xPct: Float, // 0.0 to 1.0 (from defensive goal to attacking goal)
    val yPct: Float, // 0.0 to 1.0 (from left touchline to right touchline)
    val primaryZoneName: String,
    val secondaryZones: List<String>
)

data class PersonalLife(
    val legalFullName: String,
    val dateOfBirth: String,
    val age: Int,
    val birthplace: String,
    val heightCm: Int,
    val weightKg: Int,
    val nicknames: List<String>,
    val earlyLifeAndRoots: String,
    val familyAndRelationships: String,
    val philanthropyAndCauses: String,
    val businessAndInvestments: String,
    val endorsementsAndSponsors: List<String>,
    val hobbiesAndPassions: List<String>,
    val funFactsAndTrivia: List<String>
)
