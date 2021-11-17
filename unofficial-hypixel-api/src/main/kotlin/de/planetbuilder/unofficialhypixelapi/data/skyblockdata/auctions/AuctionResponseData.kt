package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class AuctionResponseData (
    override val success: Boolean,
    val auctions: Array<SkyBlockAuction>
) : ResponseData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuctionResponseData

        if (success != other.success) return false
        if (!auctions.contentEquals(other.auctions)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + auctions.contentHashCode()
        return result
    }
}