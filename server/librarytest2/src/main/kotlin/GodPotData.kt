import kotlinx.serialization.Serializable

@Serializable
data class GodPotData(
    val activeOffersBetween: Map<String, Int>,
    val prices: List<Long>,
    val boxplotValues: Array<Double>,
    val timestamp: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GodPotData

        if (activeOffersBetween != other.activeOffersBetween) return false
        if (prices != other.prices) return false
        if (!boxplotValues.contentEquals(other.boxplotValues)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = activeOffersBetween.hashCode()
        result = 31 * result + prices.hashCode()
        result = 31 * result + boxplotValues.contentHashCode()
        return result
    }
}
