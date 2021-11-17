package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class GuildResponseData(
    override val success: Boolean,
    val guild: GuildResponseDataGuild? = null
) : ResponseData