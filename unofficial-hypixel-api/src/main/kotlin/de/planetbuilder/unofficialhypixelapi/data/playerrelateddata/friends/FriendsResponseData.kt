package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.friends

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class FriendsResponseData(
    override val success: Boolean,
    val uuid: String? = null,
    val records: Array<FriendsResponseDataRecord>,
    val cause: String? = null,
    val throttle: Boolean? = null,
    val global: Boolean? = null
) : ResponseData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FriendsResponseData

        if (success != other.success) return false
        if (uuid != other.uuid) return false
        if (!records.contentEquals(other.records)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + uuid.hashCode()
        result = 31 * result + records.contentHashCode()
        return result
    }
}