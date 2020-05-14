package cn.kraftsman.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.io.IOException

class CustomDateSerializer : JsonSerializer<DateTime?>() {
    @Throws(IOException::class)
    override fun serialize(value: DateTime?, gen: JsonGenerator, arg2: SerializerProvider?) {
        gen.writeString(formatter.print(value))
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
    }
}
