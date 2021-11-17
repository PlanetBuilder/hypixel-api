package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import kotlinx.serialization.Serializable

@Serializable
data class SkyBlockAuction(
    val _id: String? = null,
    val uuid: String,
    val auctioneer: String,
    val profile_id: String,
    val coop: Array<String>,
    val start: Long,
    val end: Long,
    val item_name: String,
    val item_lore: String,
    val extra: String,
    val category: String,
    val tier: String,
    val starting_bid: Long,
    val item_bytes: String,
    val claimed: Boolean,
    //claimed_bidders?
    val highest_bid_amount: Int,
    val bids: Array<Bid>,
    val bin: Boolean = false


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SkyBlockAuction

        if (_id != other._id) return false
        if (uuid != other.uuid) return false
        if (auctioneer != other.auctioneer) return false
        if (profile_id != other.profile_id) return false
        if (!coop.contentEquals(other.coop)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id?.hashCode() ?: 0
        result = 31 * result + uuid.hashCode()
        result = 31 * result + auctioneer.hashCode()
        result = 31 * result + profile_id.hashCode()
        result = 31 * result + coop.contentHashCode()
        return result
    }
}
