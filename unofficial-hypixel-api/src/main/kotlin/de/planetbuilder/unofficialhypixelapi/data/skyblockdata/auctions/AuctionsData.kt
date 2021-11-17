package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

class AuctionsData(
    val auctions: Array<SkyBlockAuction>
) {

    companion object {
        fun parseAuctionData(auctionResponseData: AuctionResponseData) : AuctionsData {
            return AuctionsData(auctionResponseData.auctions)
        }
    }
}