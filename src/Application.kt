package cn.kraftsman

import cn.kraftsman.entities.Diary
import cn.kraftsman.tables.Diaries
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {

        }
    }

    Database.connect(
        url = "jdbc:h2:mem:diary;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver"
    )

    transaction {
        SchemaUtils.create(Diaries)
    }

    transaction {
        for (index in 1..5) {
            val diary = Diary.new {
                content = "Sample Diary $index"
                createdAt = DateTime.now()
                updatedAt = DateTime.now()
            }
            println("Diary ${diary.id} created")
        }
    }

    routing {

        get("/") {
            call.respondText("Hello, world")
        }

    }

}
