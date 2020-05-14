package cn.kraftsman.entities

import cn.kraftsman.tables.Diaries
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Diary(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Diary>(Diaries)

    var content by Diaries.content
    var createdAt by Diaries.createdAt
    var updatedAt by Diaries.updatedAt
}
