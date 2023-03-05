package unit.houses

import houses.storeHousesPictures
import io.ktor.http.*
import models.House
import models.HousePictureResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.io.File


class UtilsTests {

    val dir = "./photosTest/"

    @Test
    fun `storeHousePictures saves HousePictures to specified parentDirectory`(){
        val hPR = HousePictureResponse(HttpStatusCode.OK,
            byteArrayOf(0x00),
            House(
                1,
                "abcd",
                "abcd",
                150.0,
                "www.abcd.com/abcd.jpg"
            )
        )
        storeHousesPictures(listOf(hPR), this.dir)
        assert(File("${this.dir}/[1] - [abcd].jpg").exists())
    }

    @AfterEach
    fun removePhotosTest(){
        val f = File(this.dir)
        f.deleteRecursively()
    }
}