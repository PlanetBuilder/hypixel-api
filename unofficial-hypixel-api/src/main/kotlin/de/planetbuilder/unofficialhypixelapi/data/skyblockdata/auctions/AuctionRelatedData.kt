package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions

import de.planetbuilder.unofficialhypixelapi.MissingAuctionsDataException
import de.planetbuilder.unofficialhypixelapi.UnofficialHypixelAPI
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request

class AuctionRelatedData {

    private val json = Json { ignoreUnknownKeys = true }

    fun getTotalAuctionsPage() : TotalAuctionsPage = json.decodeFromString(
        UnofficialHypixelAPI.client(
            Request(Method.GET, "https://api.hypixel.net/skyblock/auctions")
        ).body.toString()
    )
    fun getAuctionsResponseData(page: Int) : AuctionResponseData = json.decodeFromString(
        UnofficialHypixelAPI.client(
            Request(Method.GET, "https://api.hypixel.net/skyblock/auctions").query("page", page.toString())
        ).body.toString()
    )
    fun getAuctionsData() : AuctionsData {
        val totalPages = getTotalAuctionsPage().totalPages
        val allPages = mutableListOf<AuctionResponseData>()
        val allAuctions = mutableListOf<SkyBlockAuction>()
        for (i in 0 until totalPages) {
            allPages.add(getAuctionsResponseData(i))
            if (allPages[i].success){
                for (j in allPages[i].auctions.indices) {
                    allAuctions.add(allPages[i].auctions[j])
                }
            } else throw MissingAuctionsDataException("Error while getting auctions data")
        }
        return AuctionsData(allAuctions.toTypedArray())
    }
}