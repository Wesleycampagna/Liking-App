package com.study.liking.migrations;

import android.database.sqlite.SQLiteDatabase;

import br.com.integrati.sprinkles_inti_library.Migration;

public class CreateSuperHeroMigration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        db.execSQL(
            " CREATE TABLE super_hero ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " id_super_hero INTEGER, "  +
            " name TEXT, "  +
            " description TEXT, "  +
            " quantity_comics INTEGER, "  +
            " url_image TEXT, "  +
            " image_extension TEXT " +
            " ) "
        );
    }
}