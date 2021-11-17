package de.planetbuilder.unofficialhypixelapi

import java.io.IOException

open class MissingDataException(message: String) : RuntimeException(message)

class MissingKeyDataException(message: String) : MissingDataException(message)
class MissingFriendsDataException(message: String) : MissingDataException(message)
class MissingAuctionsDataException(message: String) : MissingDataException(message)