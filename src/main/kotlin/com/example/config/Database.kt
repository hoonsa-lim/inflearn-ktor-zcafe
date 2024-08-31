package com.example.config

import com.example.domain.CafeMenuTable
import com.example.domain.CafeOrderTable
import com.example.domain.CafeUserTable
import com.example.shared.dummyQueryList
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.h2.tools.Server
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    configureH2()
    connectDatabase()
    initData()
}

//h2: in memory db
//다른 포트를 물고 띄울 수 있게 하기 위한 설정
fun Application.configureH2() {
    val h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092")

    //ktor 문법
    environment.monitor.subscribe(ApplicationStarted) { application ->
        h2Server.start()
        application.environment.log.info("H2 server started. ${h2Server.url}")
    }

    environment.monitor.subscribe(ApplicationStopped) { application ->
        h2Server.stop()
        application.environment.log.info("H2 server stopped. ${h2Server.url}")
    }
}


private fun initData() {
    transaction {
        addLogger(StdOutSqlLogger)      //log 추가
        SchemaUtils.create(
            CafeMenuTable,
            CafeUserTable,
            CafeOrderTable,
        )
    }
}

private fun connectDatabase() {
    val dbName = "cafedb"

    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:h2:mem:$dbName"
        driverClassName = "org.h2.Driver"
        validate()
    }

    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)
}