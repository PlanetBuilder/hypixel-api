package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

import kotlinx.serialization.Serializable

@Serializable
data class GuildResponseDataGuildRank(
    val name: String,
    val default: Boolean = false,
    val priority: Int,
    val created: Long
)