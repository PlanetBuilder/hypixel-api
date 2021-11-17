package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata

class PlayerRelatedData(
    private val key: String
) {
    /*
    fun getKeyData(key: String = this.key): KeyResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/key"
                    ).query("key", key)
                ).toString()
            )[0]
        )

    fun getPlayerData(uuid: String): PlayerResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/player"
                    ).query("key", key).query("uuid", uuid)
                ).toString()
            )[0]
        )

    fun getFriendsData(uuid: String): FriendsResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/friends"
                    ).query("key", key).query("uuid", uuid)
                ).toString()
            )[0]
        )

    fun getRecentGamesData(uuid: String): RecentGamesResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/recentgames"
                    ).query("key", key).query("uuid", uuid)
                ).toString()
            )[0]
        )

    fun getStatusData(uuid: String): StatusResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/status"
                    ).query("key", key).query("uuid", uuid)
                ).toString()
            )[0]
        )

    /*
    arg: id/name/player
     */
    fun getGuildData(arg: String, guildSearchMethod: GuildSearchMethod): GuildResponseData {
        return Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/guild"
                    ).query("key", key).query(guildSearchMethod.toString().lowercase(), arg)
                ).toString()
            )[0]
        )
    }

    fun getPlayerRankedSkywarsData(uuid: String): StatusResponseData =
        Json{ignoreUnknownKeys = true}.decodeFromString(
            LocalRegex.find(
                Patterns.JSON_PATTERN,
                UnofficialHypixelAPI.client(
                    Request(
                        Method.GET, "https://api.hypixel.net/player/ranked/skywars"
                    ).query("key", key).query("uuid", uuid)
                ).toString()
            )[0]
        )

        */
}