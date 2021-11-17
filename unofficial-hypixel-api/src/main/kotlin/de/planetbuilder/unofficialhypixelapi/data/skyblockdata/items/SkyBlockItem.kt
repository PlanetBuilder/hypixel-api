package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.items

import kotlinx.serialization.Serializable

@Serializable
data class SkyBlockItem(
    val id: String,
    val material: String,
    val name: String,
    val tier: String,
    val color: String,
    val skin: String
)