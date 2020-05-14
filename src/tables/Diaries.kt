package cn.kraftsman.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.datetime

object Diaries : Table() {
    val id = integer("id")
    val time = datetime("time")
    val data = text("data")

    override val primaryKey = PrimaryKey(id, name = "PK_Diaries_ID")
}
