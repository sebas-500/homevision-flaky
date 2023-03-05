package client

import de.jensklingenberg.ktorfit.http.*
import io.ktor.client.statement.*
import models.HouseResponse

interface HomeVisionApi {

    @Headers("Content-Type: application/json")
    @GET("api_project/houses")
    suspend fun houses(@Query("page") page: Int,
                       @Query("per_page") perPage: Int?): HouseResponse

    @Streaming
    @GET()
    suspend fun housePhoto(@Url url: String): HttpStatement
}

val homeVisionApi = ktorfit.create<HomeVisionApi>()