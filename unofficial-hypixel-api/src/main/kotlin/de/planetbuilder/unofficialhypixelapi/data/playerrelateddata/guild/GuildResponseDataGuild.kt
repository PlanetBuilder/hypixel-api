package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class GuildResponseDataGuild(
    val _id: String? = null,
    val name: String? = null,
    val name_lower: String? = null,
    val coins: Long? = null,
    val coinsEver: Long? = null,
    val created: Long? = null,
    val members: Array<GuildResponseDataGuildMember>? = null,
    val ranks: Array<GuildResponseDataGuildRank>? = null,
    val achievements: JsonObject? = null,
    val exp: Long? = null,
    val guildExpByGameType: JsonObject? = null
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GuildResponseDataGuild

        if (_id != other._id) return false
        if (name != other.name) return false
        if (name_lower != other.name_lower) return false
        if (coins != other.coins) return false
        if (coinsEver != other.coinsEver) return false
        if (created != other.created) return false
        if (!ranks.contentEquals(other.ranks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + name_lower.hashCode()
        result = 31 * result + coins.hashCode()
        result = 31 * result + coinsEver.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + ranks.contentHashCode()
        return result
    }
}