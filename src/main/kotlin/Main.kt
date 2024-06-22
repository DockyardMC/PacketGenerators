import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

fun main(args: Array<String>) {
    val dataGenerator = WikiVGDataGenerator()
    log("Writing data of packets in json format to ./out/packets.json")

    val dir = File("./out/")
    dir.deleteRecursively()
    dir.mkdirs()

    val file = File("./out/packets.json")
    file.createNewFile()
    file.writeText(dataGenerator.json)

    log("Written to file successfully!", LogType.SUCCESS)
}

@Serializable
data class Packet(
    var id: String,
    val header: String,
    val packet: String,
    @SerialName("protocol_state")
    var protocolState: String,
    @SerialName("bound_to")
    var boundTo: String,
    var content: List<PacketField>
)

@Serializable
data class PacketField(
    val field: String,
    val type: String
)