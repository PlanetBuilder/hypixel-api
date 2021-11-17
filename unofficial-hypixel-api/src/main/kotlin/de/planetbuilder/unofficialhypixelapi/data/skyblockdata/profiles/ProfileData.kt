package de.planetbuilder.unofficialhypixelapi.data.skyblockdata.profiles

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    override val success: Boolean,
    val profile: SkyBlockProfile
) : ResponseData