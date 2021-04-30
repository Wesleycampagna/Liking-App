package com.study.liking.utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

import br.com.integrati.sprinkles_inti_library.typeserializers.SqlType;
import br.com.integrati.sprinkles_inti_library.typeserializers.TypeSerializer;

public class DateSerializerSprinkles implements TypeSerializer<Date> {

    @Override
    public Date unpack(Cursor c, String name) {
        long dateLong = c.getLong(c.getColumnIndexOrThrow(name));
        if(dateLong==0){
            return null;
        } else {
            return new Date(dateLong);
        }
    }

    @Override
    public void pack(Date object, ContentValues cv, String name) {
        long dateLong;
        if(object==null){
            dateLong = 0l;
        } else {
            dateLong = object.getTime();
        }
        cv.put(name, dateLong);
    }

    @Override
    public String toSql(Date object) {
        long value = 0l;
        if(object != null){
            value = object.getTime();
        }
        return String.valueOf(value);
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.INTEGER;
    }
}

