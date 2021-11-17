package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.rankedskywars

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class PlayerRankedSkywarsResponseData(
    override val success: Boolean,
    val result: PlayerRankedSkywarsResponseDataResult
) : ResponseData