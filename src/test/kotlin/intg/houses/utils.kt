package intg.houses

import houses.getHousesData
import models.House
import models.HouseResponse
import org.junit.jupiter.api.Test

class UtilsTests {

    @Test
    fun `getHousesData returns as many items as specified in range`() {
        assert(getHousesData((1..1)).size == 1)
    }

    @Test
    fun `getHousesData returns empty list if range is empty`() {
        assert(listOf<HouseResponse>() == getHousesData(IntRange.EMPTY))
    }

    @Test
    fun `downloadHousesPictures keeps going after a 404 and returns downloaded pics `() {
        val house = House(
            1,
            "fails",
            "fails",
            150.0,
            "https://www.shutterstock.com/es/image-photo/random-not-found"
        )
        val house1 = House(
            1,
            "runsOk",
            "runsOk",
            1150.0,
            "https://cdn.evbstatic.com/s3-build/fe/build/images/377efcadd26253fb9bcb57f8786f7e54-5_mobile_659x494.jpg"
        )
        val housesResponse = listOf(HouseResponse(listOf(house, house1)))
        assert(houses.downloadHousesPictures(housesResponse).size == 1)
    }
}