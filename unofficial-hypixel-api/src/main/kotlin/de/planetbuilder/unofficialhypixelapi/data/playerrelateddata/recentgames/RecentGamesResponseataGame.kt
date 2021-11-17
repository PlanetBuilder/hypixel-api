package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.recentgames

import kotlinx.serialization.Serializable

@Serializable
data class RecentGamesResponseataGame(
    val date: Long,
    val gameType: String,
    val mode: String,
    val map: String,
    val ended: Long? = null
)
