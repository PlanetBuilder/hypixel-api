package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.friends

import de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.Player

class Friend (
    val friendshipID: String,
    val player: Player,
    val friendRequestSender: Boolean,
    val friendshipStarted: Long
)