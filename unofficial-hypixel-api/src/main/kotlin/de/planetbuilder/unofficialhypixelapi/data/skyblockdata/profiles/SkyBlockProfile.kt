package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.profiles

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SkyBlockProfile(
    val profile_id: String,
    val members: JsonObject,
    val community_upgrades: JsonObject,
    val cute_name: String,
    val banking: JsonObject,
    val game_mode: String
)
