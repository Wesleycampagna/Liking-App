package com.study.liking.migrations;

import android.database.sqlite.SQLiteDatabase;

import br.com.integrati.sprinkles_inti_library.Migration;

public class CreateOwnUserMigration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        db.execSQL(
                " CREATE TABLE own_user ( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, "  +
                " last_name TEXT, "  +
                " login TEXT, "  +
                " email TEXT, " +
                " save_credentials INTEGER, " +
                " first_access INTEGER, " +
                " password TEXT " +
            " ) "
        );
    }
}
