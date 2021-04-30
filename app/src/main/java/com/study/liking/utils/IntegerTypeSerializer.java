package com.study.liking.utils;

import android.content.ContentValues;
import android.database.Cursor;

import br.com.integrati.sprinkles_inti_library.typeserializers.SqlType;
import br.com.integrati.sprinkles_inti_library.typeserializers.TypeSerializer;

public class IntegerTypeSerializer implements TypeSerializer<Integer> {
    @Override
    public Integer unpack(Cursor c, String name) {
        if(c.isNull(c.getColumnIndex(name)))
            return null;
        return c.getInt(c.getColumnIndex(name));
    }

    @Override
    public void pack(Integer object, ContentValues cv, String name) {
        cv.put(name, object);
    }

    @Override
    public String toSql(Integer object) {
        if(object==null){
            return "null";
        }
        return String.valueOf(object);
    }

    @Override
    public SqlType getSqlType() {
        return SqlType.INTEGER;
    }
}
