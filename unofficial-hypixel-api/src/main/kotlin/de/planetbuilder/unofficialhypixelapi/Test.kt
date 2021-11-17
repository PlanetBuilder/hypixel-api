package de.planetbuilder.unofficialhypixelapi

import de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.guild.GuildResponseData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json { ignoreUnknownKeys = true }

fun main(args: Array<String>) {
    val unofficialHypixelAPI = UnofficialHypixelAPI("67375154-2cff-4266-b6a8-d364e3e67e5c")
    val temp = json.decodeFromString<GuildResponseData>("""
        {
           "success":true,
           "guild":{
              "_id":"618965e08ea8c940a6e5a347",
              "name":"ProfessionelleHacker",
              "name_lower":"professionellehacker",
              "coins":0,
              "coinsEver":0,
              "created":1636394464073,
              "members":[
                 {
                    "uuid":"e5e4acd84f814872929a21e0bb74f8d7",
                    "rank":"Guild Master",
                    "joined":1636394464073,
                    "expHistory":{
                       "2021-11-08":1,
                       "2021-11-07":0,
                       "2021-11-06":5,
                       "2021-11-05":8,
                       "2021-11-04":0,
                       "2021-11-03":7,
                       "2021-11-02":45
                    }
                 },
                 {
                    "uuid":"7a6be51eb3eb4861982802c6c8e54bcc",
                    "rank":"Officer",
                    "joined":1636394596534,
                    "expHistory":{
                       "2021-11-08":423,
                       "2021-11-07":0,
                       "2021-11-06":0,
                       "2021-11-05":0,
                       "2021-11-04":0,
                       "2021-11-03":0,
                       "2021-11-02":0
                    }
                 },
                 {
                    "uuid":"776e5794c13340f282677258031a1291",
                    "rank":"Officer",
                    "joined":1636394669654,
                    "expHistory":{
                       "2021-11-08":1,
                       "2021-11-07":0,
                       "2021-11-06":5,
                       "2021-11-05":8,
                       "2021-11-04":0,
                       "2021-11-03":7,
                       "2021-11-02":45
                    }
                 }
              ],
              "ranks":[
                 {
                    "name":"Member",
                    "default":true,
                    "priority":1,
                    "created":1636394464073
                 },
                 {
                    "name":"Officer",
                    "priority":2,
                    "created":1636394464073
                 }
              ],
              "achievements":{
                 "EXPERIENCE_KINGS":423
              },
              "exp":423,
              "guildExpByGameType":{
                 "SMP":0,
                 "ARCADE":0,
                 "MCGO":0,
                 "PROTOTYPE":423,
                 "QUAKECRAFT":0,
                 "PAINTBALL":0,
                 "TNTGAMES":0,
                 "UHC":0,
                 "MURDER_MYSTERY":0,
                 "ARENA":0,
                 "WALLS3":0,
                 "BUILD_BATTLE":0,
                 "PIT":0,
                 "SUPER_SMASH":0,
                 "GINGERBREAD":0,
                 "BEDWARS":0,
                 "SKYWARS":0,
                 "VAMPIREZ":0,
                 "WALLS":0,
                 "DUELS":0,
                 "SPEED_UHC":0,
                 "HOUSING":0,
                 "SKYBLOCK":0,
                 "LEGACY":0,
                 "SURVIVAL_GAMES":0,
                 "BATTLEGROUND":0,
                 "REPLAY":0
              }
           }
        }
    """.trimIndent().replace("\\s".toRegex(), ""))
    val memberExpHistory = Array(temp.guild?.members!!.size){HashMap<String, Long?>()}
    for (j in temp.guild.members[0].expHistory.keys.indices) {
        memberExpHistory[0][temp.guild.members[0].expHistory.keys.elementAt(j)] =
            temp.guild.members[0].expHistory[temp.guild.members[0].expHistory.keys.elementAt(j)]
    }

    for (i in memberExpHistory[0].keys.indices) {
        println(memberExpHistory[0].keys.elementAt(i).toString() + ": " + memberExpHistory[0][memberExpHistory[0].keys.elementAt(i).toString()])
    }

/*
    val keyData = unofficialHypixelAPI.getKeyData("67375154-2cff-4266-b6a8-d364e3e67e5c")
    println(keyData.success)
    println(keyData.record.key)
    println(keyData.record.limit)
    println(keyData.record.owner)
    println(keyData.record.queriesInPastMin)
    println(keyData.record.totalQueries)

    val playerData = unofficialHypixelAPI.getPlayerData("776e5794-c133-40f2-8267-7258031a1291")
    println(playerData.success)
    println(playerData.player.uuid)
    println(playerData.player.displayname)
    println(playerData.player.packageRank)
    println(playerData.player.newPackageRank)
    println(playerData.player.monthlyPackageRank)
    println(playerData.player.firstLogin)
    println(playerData.player.lastLogin)
    println(playerData.player.lastLogout)

    val friendsData = unofficialHypixelAPI.getFriendsData("7a6be51eb3eb4861982802c6c8e54bcc")
    println(friendsData.success)
    println(friendsData.uuid)
    for (i in friendsData.records.indices) {
        println(friendsData.records[i]._id)
        println(friendsData.records[i].uuidSender)
        println(friendsData.records[i].uuidReceiver)
        println(friendsData.records[i].started)
    }

    val recentGamesData = unofficialHypixelAPI.getRecentGamesData("e5e4acd8-4f81-4872-929a-21e0bb74f8d7")
    println()
    println()
    println(recentGamesData.success)
    println(recentGamesData.uuid)
    for (i in recentGamesData.games.indices) {
        println()
        println(recentGamesData.games[i].date)
        println(recentGamesData.games[i].gameType)
        println(recentGamesData.games[i].mode)
        println(recentGamesData.games[i].map)
        println(recentGamesData.games[i].ended)
    }

    val statusData = unofficialHypixelAPI.getStatusData("e5e4acd8-4f81-4872-929a-21e0bb74f8d7")
    println()
    println()
    println(statusData.success)
    println(statusData.uuid)
    println(statusData.session.online)
    println(statusData.session.gameType)
    println(statusData.session.mode)
    println(statusData.session.map)

    val guildData = unofficialHypixelAPI.getGuildData("e5e4acd8-4f81-4872-929a-21e0bb74f8d7", GuildSearchMethod.PLAYER)
    println(guildData.guild?.members?.get(0)?.expHistory?.get("2021-11-03"))
     */
}