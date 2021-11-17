import kotlinx.serialization.Serializable

@Serializable
data class GodPotDataCompact(
    val offerNumbers: Array<Short>,
    val boxplotValues: Array<Float>,
    val timestamp: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GodPotDataCompact

        if (!offerNumbers.contentEquals(other.offerNumbers)) return false
        if (!boxplotValues.contentEquals(other.boxplotValues)) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offerNumbers.contentHashCode()
        result = 31 * result + boxplotValues.contentHashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}