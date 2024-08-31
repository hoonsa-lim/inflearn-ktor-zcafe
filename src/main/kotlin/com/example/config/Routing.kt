package com.example.config

import com.example.route.menuRoute
import com.example.route.orderRoute
import com.example.route.userRoute
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        route("/api") {
            menuRoute()
            userRoute()
            orderRoute()
        }

        singlePageApplication {     //프로젝트 내 리액트 프로젝트를 찾아서 index.html을 띄워줌.
            react("frontend")
        }
    }
}
