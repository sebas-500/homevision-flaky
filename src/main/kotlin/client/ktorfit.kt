package client

import configs.ApplicationConfigs as appConfigs
import de.jensklingenberg.ktorfit.Ktorfit

val ktorfit = Ktorfit
    .Builder()
    .httpClient(ktorClient)
    .baseUrl(appConfigs.HomeVision.host)
    .build()