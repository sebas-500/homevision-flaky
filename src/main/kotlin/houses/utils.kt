package houses

import client.homeVisionApi
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import models.HousePictureResponse
import models.HouseResponse

fun getHousesData(pageRange: IntRange = (1..10)): List<HouseResponse> {
    val tasks = mutableListOf<Deferred<HouseResponse>>()

    val response = runBlocking {
        pageRange.forEach {
            tasks.add(async { homeVisionApi.houses(it, null) })
        }
        tasks.awaitAll()
    }

    return response
}

fun downloadHousesPictures(houseResponses: List<HouseResponse>): List<HousePictureResponse> {
    val housesPictures = mutableListOf<HousePictureResponse>()

    houseResponses.forEach { houseResponse ->
        houseResponse.houses.forEach { house ->
            housesPictures.addAll(runBlocking {
                val tasks = mutableListOf<Deferred<HousePictureResponse>>()

                tasks.add(async {
                    homeVisionApi.housePhoto(house.photoUrl).execute {
                        HousePictureResponse(it.status, it.readBytes(), house)
                    }
                }
                )

                tasks.awaitAll()
            })
        }
    }
    return housesPictures.filter{ it.statusCode == HttpStatusCode.OK}
}

fun storeHousesPictures(housesPictures: List<HousePictureResponse>, parentDirectoryPath: String = "photos/") {
    housesPictures.forEach { hp ->
        //TODO: we could check if file extension is accurate
        val fileExtension = hp.house.photoUrl.substring(hp.house.photoUrl.indexOfLast { c -> c == '.' } + 1)
        val photoFile = java.io.File("${parentDirectoryPath}[${hp.house.id}] - [${hp.house.address}].$fileExtension")
        photoFile.parentFile.mkdirs()
        photoFile.writeBytes(hp.image)
    }
}