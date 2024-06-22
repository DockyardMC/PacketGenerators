import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.Exception

class HTMLParser(val html: String) {

    fun parseToPackets(): List<Packet> {
        log("Parsing HTML..", LogType.DEBUG)
        val result = mutableListOf<Packet>()
        val doc: Document = Jsoup.parse(html)
        val h4Elements = doc.select("h4")

        for (h4Element in h4Elements) {
            val header = h4Element.text().trim()

            var packetId = ""
            var state = ""
            var boundTo = ""
            val fields = mutableListOf<PacketField>()

            var nextSibling = h4Element.nextElementSibling()

            while (nextSibling != null && nextSibling.tagName() != "h4") {
                if (nextSibling.tagName() == "table") {
                    val tableRows = nextSibling.select("tr")
                    if(!tableRows.toString().contains("Packet ID")) break

                    val dataRow = tableRows[1]
                    val packetInfo = dataRow.select("td")

                    packetId = packetInfo[0].text()
                    state = packetInfo[1].text()
                    boundTo = packetInfo[2].text()

                    try {
                        for (rowIndex in 2 until tableRows.size) {
                            val tds = tableRows[rowIndex].select("td")
                            if (tds.size >= 3) {
                                var fieldName = tds[0].text().trim()
                                var fieldType = tds[1].text().trim()

                                fieldName = fieldName.toLowerCase().replace(" ", "_")
                                fieldType = fieldType.toSnakeCase()

                                fields.add(PacketField(fieldName, fieldType))
                            }
                        }
                    } catch (ex: Exception) {
                        log("Error when getting fields for $header", LogType.FATAL)
                    }

                }
                nextSibling = nextSibling.nextElementSibling()
            }

            val packetName = header.toSnakeCase().replace(" ", "")
            boundTo = boundTo.lowercase()
            state = state.lowercase()

            result.add(Packet(packetId, header, packetName, state, boundTo, fields))
        }
        log("Parsed ${result.size} packets from provided HTML", LogType.SUCCESS)
        return result
    }


}