package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.items

import de.planetbuilder.unofficialhypixelapi.data.ResponseData

data class ItemsData(
    override val success: Boolean,
    val lastUpdated: Long,
    val items: Array<SkyBlockItem>
) : ResponseData {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemsData

        if (success != other.success) return false
        if (lastUpdated != other.lastUpdated) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + lastUpdated.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }
}
