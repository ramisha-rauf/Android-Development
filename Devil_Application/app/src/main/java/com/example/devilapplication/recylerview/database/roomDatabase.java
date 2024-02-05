package com.example.devilapplication.recylerview.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ListItemEntity.class}, version = 2)
public abstract class roomDatabase extends RoomDatabase {

    public abstract dao Dao();

    private static volatile roomDatabase INSTANCE;

    public static roomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (roomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    roomDatabase.class, "database")
                            .addMigrations(roomDatabase.Migration1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration Migration1_2 = new Migration(1,2){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE database_table ADD COLUMN designation TEXT");
        }

    };
}
