package com.testapps.tututest.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.database.SqlStatementValidation;
import com.testapps.tututest.models.Station;

/**
 * Created by affy on 01.09.16.
 */
public class TableStation {

    public static final String TABLE_STATIONS = INamesTable.STATIONS;

    public static final String KEY_STATION_ID = INamesTable.Station.STATION_ID;
    public static final String KEY_STATION_TITLE = INamesTable.Station.STATION_TITLE;
    public static final String KEY_STATION_COUNTRY_TITLE = INamesTable.City.COUNTY_TITLE;
    public static final String KEY_STATION_POINT_LONGITUDE = INamesTable.LONGITUDE;
    public static final String KEY_STATION_POINT_LATITUDE = INamesTable.LATITUDE;
    public static final String KEY_STATION_DISTRICT_TITLE = INamesTable.City.DISTRICT_TITLE;
    public static final String KEY_STATION_CITY_ID = INamesTable.City.CITY_ID;
    public static final String KEY_STATION_CITY_TITLE = INamesTable.City.CITY_TITLE;
    public static final String KEY_STATION_REGION_TITLE = INamesTable.City.REGION_TITLE;

    private static final String CREATE_TABLE_STATION = "CREATE TABLE " + TABLE_STATIONS + "("
            + KEY_STATION_ID + " INTEGER PRIMARY KEY, "
            + KEY_STATION_TITLE + " TEXT,"
            + KEY_STATION_COUNTRY_TITLE + " TEXT,"
            + KEY_STATION_POINT_LONGITUDE + " INTEGER,"
            + KEY_STATION_POINT_LATITUDE + " INTEGER,"
            + KEY_STATION_DISTRICT_TITLE + " TEXT,"
            + KEY_STATION_CITY_ID + " INTEGER,"
            + KEY_STATION_CITY_TITLE + " TEXT,"
            + KEY_STATION_REGION_TITLE + " TEXT" + ")";

    private final SqlStatementValidation sqlStatementValidation = new SqlStatementValidation();

    public TableStation() {}

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_STATION);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TableCity.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STATIONS);
        onCreate(database);
    }

    public void add(Station station, SQLiteOpenHelper helper){
        String sql = "INSERT OR REPLACE INTO " + TABLE_STATIONS +
                " ( " + KEY_STATION_ID +
                ", " + KEY_STATION_TITLE +
                ", " + KEY_STATION_COUNTRY_TITLE +
                ", " + KEY_STATION_POINT_LONGITUDE +
                ", " + KEY_STATION_POINT_LATITUDE +
                ", " + KEY_STATION_DISTRICT_TITLE +
                ", " + KEY_STATION_CITY_ID +
                ", " + KEY_STATION_CITY_TITLE +
                ", " + KEY_STATION_REGION_TITLE +
                " ) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransactionNonExclusive();
        sqlStatementValidation.runSqlStatementDependingByApiVersion(database, sql, station);
    }

    public void add(Iterable<Station> iterable, SQLiteOpenHelper helper){
        for(Station station : iterable){
            add(station, helper);
        }
    }

    public void update(Station station){
    }

    public void remove(Station station){
    }

    public int count(SQLiteOpenHelper helper){
        int num;
        final SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String QUERY = "SELECT  * FROM " + TABLE_STATIONS;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            cursor.close();
            if (db.isOpen())
                db.close();
            return num;
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return 0;
    }



}
