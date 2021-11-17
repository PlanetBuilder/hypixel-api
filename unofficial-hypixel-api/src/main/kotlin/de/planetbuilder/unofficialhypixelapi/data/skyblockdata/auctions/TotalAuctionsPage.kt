package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class TotalAuctionsPage(
    override val success: Boolean,
    val page: Int,
    val totalPages: Int,
    val totalAuctions: Int,
    val lastUpdated: Long,
    val auctions: Array<SkyBlockAuction>
) : ResponseData