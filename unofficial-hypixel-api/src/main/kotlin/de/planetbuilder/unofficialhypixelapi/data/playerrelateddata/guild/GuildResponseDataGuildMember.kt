package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class GuildResponseDataGuildMember(
    val uuid: String,
    val rank:  String,
    val joined: Long,
    val expHistory: Map<String, Long>
)
