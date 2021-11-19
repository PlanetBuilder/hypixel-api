package items

import kotlinx.serialization.Serializable

@Serializable
data class ItemConfigData (
    val existing_items: MutableList<String>
)