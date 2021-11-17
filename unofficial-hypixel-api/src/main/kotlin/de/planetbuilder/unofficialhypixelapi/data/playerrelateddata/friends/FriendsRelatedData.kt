package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.friends

import de.planetbuilder.unofficialhypixelapi.MissingFriendsDataException
import de.planetbuilder.unofficialhypixelapi.UnofficialHypixelAPI
import de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.Player
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request

class FriendsRelatedData(
    private val key: String
) {
    fun getFriendsResponseData(uuid: String) : FriendsResponseData = Json { ignoreUnknownKeys = true }.decodeFromString(
        UnofficialHypixelAPI.client(
            Request(Method.GET, "https://api.hypixel.net/friends").query("key", key).query("uuid", uuid)).body.toString())
    fun getFriendsData(uuid: String) : FriendsData {
        val friendsResponseData = getFriendsResponseData(uuid)
        if (friendsResponseData.success) return FriendsData.parseFriendsData(friendsResponseData)
        else throw MissingFriendsDataException("Error while getting friends information\nCause: ${friendsResponseData.cause}")
    }
}