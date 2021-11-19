package items

import kotlinx.serialization.Serializable

@Serializable
data class ItemOfferData(
    val activeOffersBetween: Array<Int>,
    val activeOffersTotal: Int,
    val boxplotValues: Array<Double>,
    val timestamp: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemOfferData

        if (!activeOffersBetween.contentEquals(other.activeOffersBetween)) return false
        if (!boxplotValues.contentEquals(other.boxplotValues)) return false
        if (timestamp != other.timestamp) return false

        return true
    }
    override fun hashCode(): Int {
        var result = activeOffersBetween.contentHashCode()
        result = 31 * result + boxplotValues.contentHashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}
