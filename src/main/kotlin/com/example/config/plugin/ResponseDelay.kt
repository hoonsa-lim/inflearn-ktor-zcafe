package com.example.config.plugin

import io.ktor.server.application.*
import kotlinx.coroutines.delay

val ResponseDelayPlugin = createRouteScopedPlugin(name = "ResponseDelayPlugin") {
    onCall {
        delay(500)
    }
}