package com.study.liking;

import android.app.Application;

import com.study.liking.migrations.CreateDebugMigration;
import com.study.liking.migrations.CreateOwnUserMigration;
import com.study.liking.migrations.CreateSuperHeroMigration;
import com.study.liking.migrations.CreateUserMigration;
import com.study.liking.utils.DateSerializerSprinkles;
import com.study.liking.utils.IntegerTypeSerializer;

import java.util.Date;

import br.com.integrati.sprinkles_inti_library.Sprinkles;

public class LikingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Sprinkles sprinkles = Sprinkles.init(getApplicationContext());
        sprinkles.registerType(Date.class, new DateSerializerSprinkles());
        sprinkles.registerType(Integer.class, new IntegerTypeSerializer());
        runMigrations(sprinkles);
    }

    public void runMigrations(Sprinkles sprinkles) {
        /* sprinkles.addMigration(new Create<Table>Migration()); */
        sprinkles.addMigration(new CreateOwnUserMigration());
        sprinkles.addMigration(new CreateUserMigration());
        sprinkles.addMigration(new CreateDebugMigration());
        sprinkles.addMigration(new CreateSuperHeroMigration());
    }
}
