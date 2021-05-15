package com.study.liking.migrations;

import android.database.sqlite.SQLiteDatabase;

import br.com.integrati.sprinkles_inti_library.Migration;

public class CreateDebugMigration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        db.execSQL(
            " CREATE TABLE debug ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " url TEXT, "  +
            " debug TEXT " +
            " ) "
        );
    }
}