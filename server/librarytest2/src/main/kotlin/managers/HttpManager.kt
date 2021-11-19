package managers

import Timespan
import items.ItemDataHandler
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.NOT_IMPLEMENTED
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

class HttpManager {
    private val app: HttpHandler = routes(
        "/ping" bind GET to { handlePingRequest(it) },
        "/item-history" bind GET to {handleItemHistoryRequest(it)}
    )
    private val server = app.asServer(Jetty(9001)).start()
    private val dataHandler: ItemDataHandler = ItemDataHandler()



    private fun handlePingRequest(request: Request): Response {
        val status = if (request.queries("name").size != 1) BAD_REQUEST else OK
        val body = if (request.queries("name").size != 1) {
            "Error: Please give exactly one value of type \"name\"."
        } else {
            "Hello, ${request.queries("name")[0]}"
        }
        return Response(status).body(body)
    }
    private fun handleItemHistoryRequest(request: Request): Response {
        if (request.queries("item").size != 1) {
            return Response(BAD_REQUEST).body("{\"success\":false,\"cause\":\"[item] not or too often specified.\"}")
        } else if(request.queries("timespan").size != 1) {
            return Response(BAD_REQUEST).body("{\"success\":false,\"cause\":\"[timespan] not or too often specified.\"}")
        } else if (!Timespan.codes.contains(request.queries("timespan")[0])) {
            return Response(BAD_REQUEST).body("{\"success\":false,\"cause\":\"please use a valid [timespan].\"}")
        }

        val itemData = dataHandler.getItemData(request.queries("item")[0]!!, request.queries("timespan")[0]!!) ?: return Response(BAD_REQUEST).body("{\"success\":false,\"cause\":\"item does not exist.\"}")
        if (itemData.isEmpty()) return Response(NOT_IMPLEMENTED).body("{\"success\":false,\"cause\":\"item does not appear to be in the database. Report this at https://github.com/PlanetBuilder/hypixel-api and it may be implemented at soe point in future :^)\"}")

        return Response(OK).body("{\"success\":true,\"offerHistory\":$itemData}")
    }







}