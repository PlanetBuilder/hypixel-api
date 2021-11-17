package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.rankedskywars

import kotlinx.serialization.Serializable

@Serializable
data class PlayerRankedSkywarsResponseDataResult(
    val key: String,
    val position: Long,
    val score: Long
)
