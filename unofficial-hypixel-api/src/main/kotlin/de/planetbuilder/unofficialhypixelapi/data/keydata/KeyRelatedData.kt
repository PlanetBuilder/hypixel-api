package de.planetbuilder.unofficialhypixelapi.data.keydata

import de.planetbuilder.unofficialhypixelapi.MissingKeyDataException
import de.planetbuilder.unofficialhypixelapi.UnofficialHypixelAPI
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request

class KeyRelatedData {
    fun getKeyResponseData(key: String) : KeyResponseData = Json { ignoreUnknownKeys = true }.decodeFromString(UnofficialHypixelAPI.client(Request(Method.GET, "https://api.hypixel.net/key").query("key", key)).body.toString())
    fun getKeyData(key: String) : KeyData {
        val keyResponseData = getKeyResponseData(key)
        if (keyResponseData.success) {
            return KeyData(
                keyResponseData.record!!.key,
                keyResponseData.record.owner,
                keyResponseData.record.limit,
                keyResponseData.record.queriesInPastMin,
                keyResponseData.record.totalQueries,
            )
        } else {
            throw MissingKeyDataException("Error while getting API key information\nCause: ${keyResponseData.cause}")
        }
    }
}