package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import kotlinx.serialization.Serializable

@Serializable
data class Bid(
    val auction_id: String,
    val bidder: String,
    val profile_id: String,
    val amount: Long,
    val timestamp: Long
)