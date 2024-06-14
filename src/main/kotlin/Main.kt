import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.log
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