import houses.downloadHousesPictures
import houses.getHousesData
import houses.storeHousesPictures
import mu.KotlinLogging
import kotlin.system.measureTimeMillis

private val logger = KotlinLogging.logger {}

fun main() {
    logger.info { " ==> Process Started" }

    val elapsed =  measureTimeMillis {
        val data = getHousesData()
        val housesPictures = downloadHousesPictures(data)
        storeHousesPictures(housesPictures)
    }


    logger.info { " ==> Process Finished in $elapsed millis" }
}