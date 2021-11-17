package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.friends

import de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.Player

class FriendsData(
    val player: Player,
    val friends: Array<Friend>
) {
    companion object {
        fun parseFriendsData(friendsResponseData: FriendsResponseData) : FriendsData {
            val friends: MutableList<Friend> = mutableListOf()
            for (i in friendsResponseData.records.indices) {
                friends.add(
                    Friend(
                        friendsResponseData.records[i]._id,
                        //TODO: implement player
                        Player(),
                        friendsResponseData.uuid == friendsResponseData.records[i].uuidReceiver,
                        friendsResponseData.records[i].started

                    )
                )
            }
            return FriendsData(Player()/*TODO: implement player*/, friends.toTypedArray())
        }
    }
}