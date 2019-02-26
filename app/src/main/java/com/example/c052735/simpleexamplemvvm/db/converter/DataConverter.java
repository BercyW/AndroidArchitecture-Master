package com.example.c052735.simpleexamplemvvm.db.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null:new Date(timestamp);
    }

    @TypeConverter
    public static long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
