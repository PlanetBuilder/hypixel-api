package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.player

import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponseDataPlayer(
    val uuid: String,
    val displayname: String,
    val rank: PlayerResponseDataPlayerRank = PlayerResponseDataPlayerRank.NORMAL,
    val packageRank: PlayerResponseDataPlayerPackageRank = PlayerResponseDataPlayerPackageRank.NONE,
    val newPackageRank: PlayerResponseDataPlayerPackageRank = PlayerResponseDataPlayerPackageRank.NONE,
    val monthlyPackageRank: PlayerResponseDataPlayerMonthlyPackageRank = PlayerResponseDataPlayerMonthlyPackageRank.NONE,
    val firstLogin: Long,
    val lastLogin: Long,
    val lastLogout: Long,
    //TODO: Implement rest of player data
)
