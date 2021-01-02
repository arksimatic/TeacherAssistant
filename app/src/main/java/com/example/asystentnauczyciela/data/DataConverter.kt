package com.example.asystentnauczyciela.model

import androidx.room.TypeConverter
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}