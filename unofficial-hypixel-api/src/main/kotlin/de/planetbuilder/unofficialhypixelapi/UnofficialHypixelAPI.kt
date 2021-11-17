package de.planetbuilder.unofficialhypixelapi

import de.planetbuilder.unofficialhypixelapi.data.keydata.KeyRelatedData
import de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.PlayerRelatedData
import de.planetbuilder.unofficialhypixelapi.data.skyblockdata.SkyBlockRelatedData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request

class UnofficialHypixelAPI(
    private val key : String
) {
    companion object {
        val client = ApacheClient()
    }
    private val apiKeyValid: Boolean

    val playerRelatedData = PlayerRelatedData(key)
    //TODO: Add resources
    val skyBlockData = SkyBlockRelatedData(key)

    val keyRelatedData = KeyRelatedData()

    init {
        // Check API key validity
        val response = client(Request(Method.GET, "https://api.hypixel.net/key").query("key", key))
        if (response.status.code != 200) {
            apiKeyValid = false
            println("\u001B[33mWarning: Error while getting API key information. Most of the functions are not available\u001B[0m")
            val json = Json.decodeFromString<JsonObject>(response.body.toString())
            println("\u001B[33mCause: ${json["cause"]}\u001B[0m")
            if(response.status.code == 429) {
                println("\u001B[33mThrottle: ${json["throttle"]}\u001B[0m")
                println("\u001B[33mGlobal Throttle: ${json["global"]}\u001B[0m")
            }
        } else apiKeyValid = true
    }
}