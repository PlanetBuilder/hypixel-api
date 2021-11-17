package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.bazaar

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable

data class BazaarData(
    override val success: Boolean,
    val lastUpdated: Long,
    val products: JsonObject

) : ResponseData