package com.example

import com.example.data.PlayerRepository
import com.example.data.RealtimeMatchEngine
import com.example.model.PositionCategory
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun playerRepository_loadsSuperstars() {
        val players = PlayerRepository.players
        assertTrue("Players list should not be empty", players.isNotEmpty())

        val messi = PlayerRepository.getPlayerById("messi")
        assertNotNull("Lionel Messi must be present", messi)
        assertEquals("Lionel Messi", messi?.name)
        assertTrue("Messi must have 8 Ballon d'Ors", messi?.trophies?.ballonDor == 8)
        assertTrue("Messi must have personal life data", messi?.personalLife?.legalFullName?.isNotBlank() == true)

        val ronaldo = PlayerRepository.getPlayerById("ronaldo")
        assertNotNull("Cristiano Ronaldo must be present", ronaldo)
        assertTrue("Ronaldo career goals must be 900+", (ronaldo?.careerTotals?.totalGoals ?: 0) >= 900)
    }

    @Test
    fun prominentPlayers_areHighlighted() {
        val prominent = PlayerRepository.getProminentPlayers()
        assertTrue("There must be highlighted prominent players", prominent.isNotEmpty())
        assertTrue("All prominent players must have isProminent = true", prominent.all { it.isProminent })
    }

    @Test
    fun filterPlayers_filtersCorrectly() {
        val forwards = PlayerRepository.filterPlayers(category = PositionCategory.FORWARD)
        assertTrue("Forwards list should contain strikers and wingers", forwards.isNotEmpty())
        assertTrue("Every player in forwards must have FORWARD category", forwards.all { it.position.category == PositionCategory.FORWARD })

        val searchResult = PlayerRepository.filterPlayers(query = "Real Madrid")
        assertTrue("Should find players at Real Madrid", searchResult.isNotEmpty())
    }

    @Test
    fun realtimeMatchEngine_initializesWithMatches() {
        val matches = RealtimeMatchEngine.matchesState.value
        assertTrue("Realtime match engine should have live matches", matches.isNotEmpty())

        val initialScore = matches[0].homeScore
        RealtimeMatchEngine.triggerInstantGoal()
        val updatedMatches = RealtimeMatchEngine.matchesState.value
        assertEquals("Instant goal must increment home score", initialScore + 1, updatedMatches[0].homeScore)
    }
}
