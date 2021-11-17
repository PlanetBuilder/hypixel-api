package de.planetbuilder.unofficialhypixelapi.data.keydata

import de.planetbuilder.unofficialhypixelapi.data.ResponseData
import kotlinx.serialization.Serializable

@Serializable
data class KeyResponseData (
    override val success: Boolean,
    val record: KeyResponseDataRecord? = null,
    val cause: String? = null,
    val throttle: Boolean? = null,
    val global: Boolean? = null
) : ResponseData