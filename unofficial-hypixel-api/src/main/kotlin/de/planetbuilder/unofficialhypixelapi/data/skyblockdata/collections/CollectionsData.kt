package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.collections

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class CollectionsData(
    override val success: Boolean,
    val lastUpdated: Long,
    val version: String,
    val collections: JsonObject
) : ResponseData
