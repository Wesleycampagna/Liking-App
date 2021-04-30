package com.study.liking.migrations;

import android.database.sqlite.SQLiteDatabase;

import br.com.integrati.sprinkles_inti_library.Migration;

public class CreateUserMigration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        db.execSQL(
            " CREATE TABLE user ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT, "  +
            " last_name TEXT, "  +
            " email TEXT, " +
            " phone_number TEXT, " +
            " birth_date TEXT, " +
            " password TEXT, " +
            " avatar TEXT " +
            " ) "
        );
    }
}
