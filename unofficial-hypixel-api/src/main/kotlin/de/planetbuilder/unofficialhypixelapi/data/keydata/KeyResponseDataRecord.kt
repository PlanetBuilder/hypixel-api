package de.planetbuilder.unofficialhypixelapi.data.keydata

import kotlinx.serialization.Serializable

@Serializable
data class KeyResponseDataRecord(
    val key: String,
    val owner: String,
    val limit: Int,
    val queriesInPastMin: Int,
    val totalQueries: Long
)