package de.planetbuilder.unofficialhypixelapi.data.playerrelateddata.status

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class StatusResponseData(
    override val success: Boolean,
    val uuid: String,
    val session: StatusResponseDataSession
) : ResponseData