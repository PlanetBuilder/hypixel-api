package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.player

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponseData(
    override val success: Boolean,
    val player: PlayerResponseDataPlayer
) : ResponseData
