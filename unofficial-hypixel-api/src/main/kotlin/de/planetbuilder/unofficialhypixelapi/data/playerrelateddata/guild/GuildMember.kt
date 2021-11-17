package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

class GuildMember(
    val uuid: String,
    val rank: String,
    val joined: Long,
    val expHistory: Map<String, Long>
)