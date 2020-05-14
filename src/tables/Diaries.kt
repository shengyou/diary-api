package cn.kraftsman.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object Diaries : IntIdTable() {
    val content = text("content")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
