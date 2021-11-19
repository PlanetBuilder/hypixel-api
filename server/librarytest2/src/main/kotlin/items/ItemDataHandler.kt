package items

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class ItemDataHandler {
    private val itemConfigData = getConfig()

    fun getConfig(): ItemConfigData = Json.decodeFromString(File("items/item-config.json").readText())
    fun updateConfig(itemConfigData: ItemConfigData) {
        File("items/item-config.json").writeText(Json.encodeToString(ItemConfigData))
    }

    fun setItemData(itemName: String, data: ItemData) {
        File("items/$itemName").createNewFile()
        if (!itemConfigData.existing_items.contains(itemName)) {
            itemConfigData.existing_items.add(itemName)
            updateConfig(itemConfigData)
        }
        File("items/$itemName").writeText(Json.encodeToString(data))
    }

    fun getItemData(itemName: String, timespan: String): String? = if (!itemConfigData.existing_items.contains(itemName)) null else try {
        when (timespan) {
            "day" -> Json.encodeToString(Json.decodeFromString<ItemData>(File("items/$itemName").readText()).offerHistoryDay)
            "week" -> Json.encodeToString(Json.decodeFromString<ItemData>(File("items/$itemName").readText()).offerHistoryWeek)
            "month" -> Json.encodeToString(Json.decodeFromString<ItemData>(File("items/$itemName").readText()).offerHistoryMonth)
            "year" -> Json.encodeToString(Json.decodeFromString<ItemData>(File("items/$itemName").readText()).offerHistoryYear)
            "ever" -> Json.encodeToString(Json.decodeFromString<ItemData>(File("items/$itemName").readText()).offerHistoryEver)
            else -> "t"
        }
    } catch (e: Exception) { "" }
}