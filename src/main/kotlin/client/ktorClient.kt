package client

import configs.ApplicationConfigs as appConfigs
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

val ktorClient = HttpClient(Apache) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        logger = logger
        level = if(appConfigs.Environ.env == "dev") LogLevel.ALL else LogLevel.INFO
    }
    install(HttpRequestRetry){
        retryOnServerErrors(maxRetries = appConfigs.HomeVision.retries)
        exponentialDelay()
    }
}
