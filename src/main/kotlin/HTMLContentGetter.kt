import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.log
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class HTMLContentGetter {

    var html: String
    private val httClient: HttpClient = HttpClient.newHttpClient()

    init {
        log("Getting HTML from wiki.vg/protocol...", LogType.NETWORK)
        val request = HttpRequest.newBuilder()
            .uri(URI("https://wiki.vg/Protocol"))
            .GET()
            .build()

        val response = httClient.send(request, BodyHandlers.ofString())
        html = response.body()

        log("Successfully gotten HTML data! (size: ${html.toByteArray().size} bytes)", LogType.SUCCESS)
    }
}