package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.recentgames

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class RecentGamesResponseData(
    override val success: Boolean,
    val uuid: String,
    val games: Array<RecentGamesResponseataGame>
) : ResponseData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecentGamesResponseData

        if (success != other.success) return false
        if (uuid != other.uuid) return false
        if (!games.contentEquals(other.games)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + uuid.hashCode()
        result = 31 * result + games.contentHashCode()
        return result
    }
}