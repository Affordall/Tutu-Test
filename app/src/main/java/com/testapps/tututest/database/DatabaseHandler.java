package com.testapps.tututest.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.database.tables.TableStation;
import com.testapps.tututest.models.City;
import com.testapps.tututest.models.Station;

import javax.inject.Inject;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tutu_db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHandler sInstance;

    @Inject TableCity tableCity;
    @Inject TableStation tableStation;

    /**
     * Use the application context, which will ensure that you
     * don't accidentally leak an Activity's context.
     * See this article for more information: http://bit.ly/6LRzfx
     */
    public static synchronized DatabaseHandler getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        TutuTestApp.get(TutuTestApp.getContext()).getAppComponent().inject(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableCity.onCreate(db);
        TableStation.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TableCity.onUpgrade(db, oldVersion, newVersion);
        TableStation.onUpgrade(db, oldVersion, newVersion);
    }

    public boolean isDBlocked() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.isDbLockedByCurrentThread();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TableCity.TABLE_CITIES, null, null);
            db.delete(TableStation.TABLE_STATIONS, null, null);
        } catch (SQLiteDatabaseLockedException e) {
            e.printStackTrace();
        } finally {
            if (db.isOpen())
                db.close();
        }
    }

    public Boolean doIt(String TableName, String ColumnName, Object ColumnData) {
        boolean outValue = false;
        try {
            outValue = doesRecordExist(TableName, ColumnName, ColumnData);
        } catch (SQLException e) {
            // It's fine if findUser throws a NPE
        }
        return outValue;
    }

    public Boolean doesRecordExist(String TableName, String ColumnName, Object ColumnData) throws SQLException {
        String q = "Select * FROM "+ TableName + " WHERE " + ColumnName + "='" + ColumnData + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        try {
            return cursor.moveToFirst();
        } finally {
            cursor.close();
            if (db.isOpen())
                db.close();
        }
    }

    /**
     * CRUD Operations
     * */

    /** City */

    public boolean isCityExist(long id) {
        return doIt(TableCity.TABLE_CITIES, TableCity.KEY_COUNTRY_CITY_ID, id);
    }

    public void addCity(City city) {
        if (!isCityExist(city.getCountryCityId()))
        tableCity.add(city, this);
    }

    public void updateCity(City city) {
        tableCity.update(city);
    }

    public void removeCity(City city) {
        tableCity.remove(city);
    }

    public int getCitiesCount() {
        return tableCity.count(this);
    }

    /** Station */

    public void addStation(Station station) {
        tableStation.add(station, this);
    }

    public void updateStation(Station station) {
        tableStation.update(station);
    }

    public void removeStation(Station station) {
        tableStation.remove(station);
    }

    public int getStationsCount() {
        return tableStation.count(this);
    }
}
