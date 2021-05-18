package com.study.liking.migrations;

import android.database.sqlite.SQLiteDatabase;

import br.com.integrati.sprinkles_inti_library.Migration;

public class UpdateSuperHero1Migration extends Migration {
    @Override
    protected void doMigration(SQLiteDatabase db) {
        db.execSQL(" alter table super_hero add column off_set INTEGER; ");
        db.execSQL(" alter table super_hero add column count INTEGER; ");
        db.execSQL(" alter table super_hero add column total INTEGER; ");
    }
}
