package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.profiles

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class PlayerProfilesData(
    override val success: Boolean,
    val profiles: Array<SkyBlockProfile>
) : ResponseData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerProfilesData

        if (success != other.success) return false
        if (!profiles.contentEquals(other.profiles)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + profiles.contentHashCode()
        return result
    }
}