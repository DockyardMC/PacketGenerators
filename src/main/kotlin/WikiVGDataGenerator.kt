import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.log
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class WikiVGDataGenerator {

    var packets: List<HTMLParser.Packet> = mutableListOf()
    var json: String = ""

    init {
        log("Starting wiki.vg data generator", LogType.DEBUG)
        val htmlContentGetter = HTMLContentGetter()
        val htmlParser = HTMLParser(htmlContentGetter.html)
        packets = htmlParser.parseToPackets()

        val pretty = Json {
            prettyPrint = true
            prettyPrintIndent = "    "
        }
        json = pretty.encodeToString<List<HTMLParser.Packet>>(packets)
    }
}