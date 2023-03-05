package models

import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HouseResponse(
    @SerialName("houses") val houses: List<House>
)

@Serializable
data class House(
    @SerialName("id") val id: Int,
    @SerialName("address") val address: String,
    @SerialName("homeowner") val homeOwner: String,
    @SerialName("price") val price: Double,
    @SerialName("photoURL") val photoUrl: String
)

data class HousePictureResponse(
    val statusCode: HttpStatusCode,
    val image: ByteArray,
    val house: House
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HousePictureResponse

        if (statusCode != other.statusCode) return false
        if (!image.contentEquals(other.image)) return false
        if (house != other.house) return false

        return true
    }

    override fun hashCode(): Int {
        var result = statusCode.hashCode()
        result = 31 * result + image.contentHashCode()
        result = 31 * result + house.hashCode()
        return result
    }
}