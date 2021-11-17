package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import java.util.ArrayList
import java.util.HashMap

class GuildData(
    val id: String,
    val name: String,
    val coins: Long,
    val coinsEver: Long,
    val created: Long,
    val members: Array<GuildMember>,
    val ranks: Array<GuildRank>,
    val achievements: Map<String, Long>,
    val exp: Long,
    val guildExpByGameType: Map<String, Long>
) {
    fun temp() {
        for (i in members[0].expHistory.keys.indices) {
            println(members[0].expHistory.keys.elementAt(i))
        }
    }
/*
    companion object {
        fun parseGuildData(guildResponseData: GuildResponseData?) : GuildData {

            val memberUUIDs = Array(guildResponseData?.guild?.members?.size!!){""}
            val memberRanks = Array(guildResponseData.guild.members.size){""}
            val memberJoinTimes: Array<Long> = Array(guildResponseData.guild.members.size){0}
            val memberExpHistory = Array(guildResponseData.guild.members.size){HashMap<String, Long>()}
            for (i in guildResponseData.guild.members.indices) {
                memberUUIDs[i] = guildResponseData.guild.members[i].uuid
                memberRanks[i] = guildResponseData.guild.members[i].rank
                memberJoinTimes[i] = guildResponseData.guild.members[i].joined
                for (j in guildResponseData.guild.members[i].expHistory.values.indices) {
                    memberExpHistory[i][guildResponseData.guild.members[i].expHistory.keys.elementAt(i)] =
                        guildResponseData.guild.members[i].expHistory[guildResponseData.guild.members[i].expHistory.keys.elementAt(i).]
                }
            }


            return GuildData(
                guildResponseData.guild._id!!,
                guildResponseData.guild.name!!,
                guildResponseData.guild.coins!!,
                guildResponseData.guild.coinsEver!!,
                guildResponseData.guild.created!!,
                guildResponseData.guild.members!!,
                guildResponseData.guild.ranks!!,
                guildResponseData.guild.achievements!!,
                guildResponseData.guild.exp!!,
                guildResponseData.guild.guildExpByGameType!!,
            )

        }
    }

 */
}