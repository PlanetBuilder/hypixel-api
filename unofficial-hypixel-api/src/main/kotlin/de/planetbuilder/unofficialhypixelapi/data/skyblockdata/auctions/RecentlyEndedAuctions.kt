package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.json.JsonObject

data class RecentlyEndedAuctions(
    override val success: Boolean,
    val lastUpdated: Long,
    val auctions: JsonObject
) : ResponseData