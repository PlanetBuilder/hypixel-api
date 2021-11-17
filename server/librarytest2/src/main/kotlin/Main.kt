import com.google.common.base.Charsets
import com.google.common.io.FileWriteMode
import com.google.common.io.Files
import com.google.common.primitives.Longs
import com.google.common.primitives.Shorts
import de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions.AuctionRelatedData
import de.planetbuilder.unofficialhypixelapi.data.skyblockdata.auctions.SkyBlockAuction
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.routing.bind
import org.http4k.routing.websockets
import org.http4k.server.Jetty
import org.http4k.server.PolyHandler
import org.http4k.server.asServer
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsHandler
import org.http4k.websocket.WsMessage
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.measureTimeMillis

val requestingPath = Path.of("requesting")
val websocketsHandler: MutableList<(String) -> Unit> = mutableListOf()

fun main() {
    val ws: WsHandler = websockets("/{requesting}" bind { ws: Websocket -> ws.send(WsMessage(Json.encodeToString(allData("compact-god-pot-data.bin"))))})
    val http = {_: Request -> Response(OK).body("This is websockets lol") }
    PolyHandler(http, ws).asServer(Jetty(9000)).start()
    println("started on port 9000")

    println(Date(System.currentTimeMillis()).toString())

    val timer = Timer()
    timer.schedule(UpdateGodPotData(), 0, 60000)

    while (true) {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
                addDataToFile("errors",Date(System.currentTimeMillis()).toString() + "\n\n" + e.stackTraceToString() + "\n\n\n\n\n")
            }
    }
}

class UpdateGodPotData : TimerTask() {
    override fun run() {
        try {
            println("\nupdating...")
            println("Time update took: ${measureTimeMillis {
                val auctionListener = AuctionRelatedData()
                val data = try {
                    auctionListener.getAuctionsData()
                } catch (e: SerializationException) {
                    e.printStackTrace()
                    addDataToFile("errors",Date(System.currentTimeMillis()).toString() + "\n\n" + e.stackTraceToString() + "\n\n\n\n\n")
                    return
                }
                val godPotOffers = mutableListOf<SkyBlockAuction>()
                for (i in data.auctions.indices) {
                    if (data.auctions[i].item_name == "God Potion" && data.auctions[i].bin && data.auctions[i].bids.isEmpty()) godPotOffers.add(
                        data.auctions[i]
                    )
                }
                godPotOffers.sortBy { it.starting_bid }

                var index = godPotOffers.size-1
                while (index >= 0) {
                    if (godPotOffers[index].starting_bid > godPotOffers[0].starting_bid+500000) godPotOffers.removeAt(index)
                    index--
                }

                val prices = mutableListOf<Long>()
                for (i in godPotOffers.indices) prices.add(godPotOffers[i].starting_bid)

                val boxplotValues = Array(6){0.0}
                for (i in boxplotValues.indices) boxplotValues[i] = getBoxplotValues(godPotOffers)["q$i"]!!

                println("total active offers under ${godPotOffers[0].starting_bid+500000}: ${godPotOffers.size}")

                val godPotData = GodPotData(getActiveOffersBetween(godPotOffers), prices, boxplotValues, System.currentTimeMillis())
                
                
                writeBinaryFile(godPotData, "data-validity-test.bin")
                try {
                    Json.encodeToString(allData("data-validity-test.bin"))
                    java.nio.file.Files.delete(java.nio.file.Path.of("data-validity-test.bin"))
                    writeBinaryFile(godPotData, "compact-god-pot-data.bin")
                } catch (e: Exception) {
                    java.nio.file.Files.delete(java.nio.file.Path.of("data-validity-test.bin"))
                }
                
            }}ms")
            println(Date(System.currentTimeMillis()).toString())
        } catch (e: Exception) {
            println("Error occurred.")
            e.printStackTrace()
            addDataToFile("errors",Date(System.currentTimeMillis()).toString() + "\n\n" + e.stackTraceToString() + "\n\n\n\n\n")
        }
        println()
    }
}



fun addDataToFile(filePath: String, message: String) {
    try {
        File(filePath).createNewFile()
        Files.asCharSink(File(filePath), Charsets.UTF_8, FileWriteMode.APPEND).write(message)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun allData(filePath: String) : Array<GodPotDataCompact> {
    Json.encodeToString(readBinaryFile(File(filePath).length()/56-1, filePath))
    val allData = mutableListOf<GodPotDataCompact>()
    for (i in 0 until File(filePath).length()/56) {
        allData.add(readBinaryFile(i, filePath))
    }
    return allData.toTypedArray()
}

fun readBinaryFile(index: Long, filePath: String) : GodPotDataCompact {
    val bytes: ByteArray = Files.asByteSource(File(filePath)).read()
    var i = 0
    val activeOffersBetween = Array<Short>(12) {0}
    while (i < 24) {
        activeOffersBetween[i/2] = Shorts.fromBytes(bytes[index.toInt()*56+i], bytes[index.toInt()*56+i+1])
        i+=2
    }
    val boxplotValues = Array(6){0f}
    while (i < 48) {
        boxplotValues[(i-24)/4] = ByteBuffer.wrap(arrayOf(bytes[index.toInt()*56+i],bytes[index.toInt()*56+i+1],bytes[index.toInt()*56+i+2],bytes[index.toInt()*56+i+3]).toByteArray()).float
        i+=4
    }
    val timestamp = Longs.fromByteArray(bytes.slice(index.toInt()*56+48 until index.toInt()*56+56).toByteArray())
    return GodPotDataCompact(activeOffersBetween, boxplotValues, timestamp)
}

fun writeBinaryFile(godPotData: GodPotData, filePath: String) {
    val godPotDataCompact = GodPotDataCompact(
        arrayOf(
            godPotData.activeOffersBetween["u600000"]!!.toShort(),
            godPotData.activeOffersBetween["600001u649998"]!!.toShort(),
            godPotData.activeOffersBetween["649999u650000"]!!.toShort(),
            godPotData.activeOffersBetween["650001u689999"]!!.toShort(),
            godPotData.activeOffersBetween["690000u698998"]!!.toShort(),
            godPotData.activeOffersBetween["698999u700000"]!!.toShort(),
            godPotData.activeOffersBetween["700001u749998"]!!.toShort(),
            godPotData.activeOffersBetween["749999u750000"]!!.toShort(),
            godPotData.activeOffersBetween["750001u789999"]!!.toShort(),
            godPotData.activeOffersBetween["790000u798998"]!!.toShort(),
            godPotData.activeOffersBetween["798999u800000"]!!.toShort(),
            godPotData.activeOffersBetween["o800001"]!!.toShort()
        ),
        arrayOf(
            godPotData.boxplotValues[0].toFloat(),
            godPotData.boxplotValues[1].toFloat(),
            godPotData.boxplotValues[2].toFloat(),
            godPotData.boxplotValues[3].toFloat(),
            godPotData.boxplotValues[4].toFloat(),
            godPotData.boxplotValues[5].toFloat()
        ),
        godPotData.timestamp
    )

    val bytes = ByteBuffer.allocate(56)
    for (i in godPotDataCompact.offerNumbers.indices) {
        bytes.putShort(godPotDataCompact.offerNumbers[i])
    }
    for (i in godPotDataCompact.boxplotValues.indices) {
        bytes.putFloat(godPotDataCompact.boxplotValues[i])
    }
    bytes.putLong(godPotDataCompact.timestamp)
    val byteArray = ByteArray(56)
    for (i in byteArray.indices) {
        byteArray[i] = bytes[i]
    }
    try {
        File(filePath).createNewFile()
        Files.asByteSink(File(filePath), FileWriteMode.APPEND).write(byteArray)
    } catch (e: IOException) {
        addDataToFile("errors",Date(System.currentTimeMillis()).toString() + "\n\n" + e.stackTraceToString() + "\n\n\n\n\n")
        e.printStackTrace()
    }
}

fun getActiveOffersBetween(offers: MutableList<SkyBlockAuction>): HashMap<String, Int> {
    val activeOffersBetween = HashMap<String, Int>()
    activeOffersBetween["u600000"] = valuesBetween(offers, 0, 600000)
    activeOffersBetween["600001u649998"] = valuesBetween(offers, 600001, 649998)
    activeOffersBetween["649999u650000"] = valuesBetween(offers, 649999, 650000)
    activeOffersBetween["650001u689999"] = valuesBetween(offers, 650001, 689999)
    activeOffersBetween["690000u698998"] = valuesBetween(offers, 690000, 698998)
    activeOffersBetween["698999u700000"] = valuesBetween(offers, 698999, 700000)
    activeOffersBetween["700001u749998"] = valuesBetween(offers, 700001, 749998)
    activeOffersBetween["749999u750000"] = valuesBetween(offers, 749999, 750000)
    activeOffersBetween["750001u789999"] = valuesBetween(offers, 750001, 789999)
    activeOffersBetween["790000u798998"] = valuesBetween(offers, 790000, 798998)
    activeOffersBetween["798999u800000"] = valuesBetween(offers, 798999, 800000)
    activeOffersBetween["o800001"] = valuesBetween(offers, 798999, 10000000)
    return activeOffersBetween
}

fun valuesBetween(auctions: MutableList<SkyBlockAuction>, min: Long, max: Long) : Int {
    var valuesBetween = 0
    for (i in auctions.indices) if (auctions[i].starting_bid in min..max) valuesBetween++
    return valuesBetween
}

fun getBoxplotValues(offers: MutableList<SkyBlockAuction>): HashMap<String, Double>{
    val ret = HashMap<String, Double>()
    ret["q0"] = offers[0].starting_bid.toDouble()
    ret["q4"] = offers[offers.lastIndex].starting_bid.toDouble()
    if (offers.size % 2 != 0) {
        ret["q2"] = offers[(offers.size-1)/2].starting_bid.toDouble()
        if ((offers.size+1)/2 % 2 != 0) {
            ret["q1"] = offers[(offers.size-1)/4].starting_bid.toDouble()
            ret["q3"] = offers[offers.size-((offers.size+3)/4)].starting_bid.toDouble()
        } else {
            ret["q1"] = (offers[(offers.size+1)/4].starting_bid.toDouble() + offers[(offers.size-3)/4].starting_bid.toDouble())/2
            ret["q3"] = (offers[offers.size-(offers.size+5)/4].starting_bid.toDouble() + offers[offers.size-(offers.size+1)/4].starting_bid.toDouble())/2
        }
    } else {
        ret["q2"] = (offers[offers.size/2-1].starting_bid.toDouble() + offers[offers.size/2].starting_bid.toDouble())/2
        if (offers.size/2 % 2 != 0) {
            ret["q1"] = offers[(offers.size-2)/4].starting_bid.toDouble()
            ret["q3"] = offers[offers.size-((offers.size+2)/4)].starting_bid.toDouble()
        } else {
            ret["q1"] = (offers[offers.size/4].starting_bid.toDouble() + offers[offers.size/4-1].starting_bid.toDouble())/2
            ret["q3"] = (offers[offers.size-(offers.size/4+1)].starting_bid.toDouble() + offers[offers.size-(offers.size/4)].starting_bid.toDouble())/2
        }
    }
    ret["q5"] = 0.0
    for (i in 0 until offers.size/10) {
        ret["q5"] = ret["q5"]!!.plus(offers[i].starting_bid.toDouble())
    }
    ret["q5"] = ret["q5"]!!.div(offers.size/10)

    return ret
}