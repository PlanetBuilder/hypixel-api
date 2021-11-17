package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.skills

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SkillsData(
    override val success: Boolean,
    val lastUpdated: Long,
    val version: String,
    val skills: JsonObject
) : ResponseData