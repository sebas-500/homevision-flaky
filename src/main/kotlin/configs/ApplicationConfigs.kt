package configs

import java.util.*

object ApplicationConfigs {

    val props = Properties().apply {
        ApplicationConfigs::class.java.classLoader.getResourceAsStream("application.properties").use { stream ->
            load(stream)
        }
    }

    object HomeVision {
        val retries = props["homevision.retries"].toString().toInt()
        val host = props["homevision.host"] as String
    }

    object Environ {
        val env = props["environment"] as String
    }
}