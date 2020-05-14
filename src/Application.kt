package cn.kraftsman

import cn.kraftsman.entities.DataWrapper
import cn.kraftsman.entities.Diary
import cn.kraftsman.entities.Status
import cn.kraftsman.tables.Diaries
//import com.fasterxml.jackson.datatype.joda.JodaModule
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
//            registerModule(JodaModule())
        }
    }

    Database.connect(
        url = "jdbc:h2:mem:diary;DB_CLOSE_DELAY=-1",
        driver = "org.h2.Driver"
    )

    transaction {
        SchemaUtils.create(Diaries)
    }

    routing {

        get("/") {
            call.respondText("Hello, Ktor")
        }

        get("/api/v1/GetDiaryList") {
            val diaries = transaction {
                Diaries.selectAll().orderBy(Diaries.id to SortOrder.ASC).map {
                    Diary(
                        id = it[Diaries.id],
                        time = it[Diaries.time],
                        data = it[Diaries.data]
                    )
                }
            }

            call.respond(mapOf("data" to diaries))
        }

        post("/api/v1/PushDiaryList") {
            val request = call.receive<DataWrapper>()

            try {
                transaction {
                    Diaries.deleteAll()

                    request.data.forEach { diaryRequest ->
                        Diaries.insert {
                            it[id] = diaryRequest.id
                            it[time] = diaryRequest.time
                            it[data] = diaryRequest.data
                        }
                    }
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, Status(0, "Something went wrong"))
            }

            call.respond(Status(1))
        }

    }

}
