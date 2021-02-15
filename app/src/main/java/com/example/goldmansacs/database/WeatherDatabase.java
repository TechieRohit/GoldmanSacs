package com.example.goldmansacs.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.goldmansacs.utils.Constants;
import com.example.goldmansacs.model.DataClass;

@Database(entities = DataClass.WeatherDao.class, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public static WeatherDatabase instance;

    public abstract WeatherDao weatherDao();

    /**
     * A singelton instance of the database
     * @param context
     * @return
     */
    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WeatherDatabase.class, Constants.Database.getDATABASE_NAME())
                    .fallbackToDestructiveMigration()
                    .addCallback(databaseCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    /**
     * Callback when database has created
     */
    private static Callback databaseCallback;

    static {
        databaseCallback = new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        };
    }

}
