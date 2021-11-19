package items

import kotlinx.serialization.Serializable

@Serializable
data class ItemData(
    val name: String,
    val offerHistoryDay: Array<ItemOfferData>,
    val offerHistoryWeek: Array<ItemOfferData>,
    val offerHistoryMonth: Array<ItemOfferData>,
    val offerHistoryYear: Array<ItemOfferData>,
    val offerHistoryEver: Array<ItemOfferData>
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemData

        if (name != other.name) return false
        if (!offerHistoryDay.contentEquals(other.offerHistoryDay)) return false
        if (!offerHistoryWeek.contentEquals(other.offerHistoryWeek)) return false
        if (!offerHistoryMonth.contentEquals(other.offerHistoryMonth)) return false
        if (!offerHistoryYear.contentEquals(other.offerHistoryYear)) return false
        if (!offerHistoryEver.contentEquals(other.offerHistoryEver)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + offerHistoryDay.contentHashCode()
        result = 31 * result + offerHistoryWeek.contentHashCode()
        result = 31 * result + offerHistoryMonth.contentHashCode()
        result = 31 * result + offerHistoryYear.contentHashCode()
        result = 31 * result + offerHistoryEver.contentHashCode()
        return result
    }
}