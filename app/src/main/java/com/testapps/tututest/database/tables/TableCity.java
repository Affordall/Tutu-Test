package com.testapps.tututest.database.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;

import com.testapps.tututest.app.TutuTestApp;
import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.database.SqlStatementValidation;
import com.testapps.tututest.models.City;

import javax.inject.Inject;

/**
 * Created by affy on 01.09.16.
 */
public class TableCity {

    public static final String TABLE_CITIES = INamesTable.CITY;

    public static final String KEY_COUNTRY_CITY_ID = INamesTable.City.CITY_ID;
    public static final String KEY_COUNTRY_CITY_TITLE = INamesTable.City.CITY_TITLE;
    public static final String KEY_COUNTRY_REGION_TITLE = INamesTable.City.REGION_TITLE;
    public static final String KEY_COUNTRY_TITLE = INamesTable.City.COUNTY_TITLE;
    public static final String KEY_COUNTRY_POINT_LONGITUDE = INamesTable.LONGITUDE;
    public static final String KEY_COUNTRY_POINT_LATITUDE = INamesTable.LATITUDE;
    public static final String KEY_COUNTRY_DISTRICT_TITLE = INamesTable.City.DISTRICT_TITLE;
    public static final String KEY_COUNTRY_CITY_STATIONS = INamesTable.STATIONS;
    public static final String KEY_CITY_FROM_OR_TO = INamesTable.City.FROM_OR_TO;

    private static final String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_CITIES + "("
            + KEY_COUNTRY_CITY_ID + " INTEGER PRIMARY KEY, "
            + KEY_COUNTRY_CITY_TITLE + " TEXT,"
            + KEY_COUNTRY_REGION_TITLE + " TEXT,"
            + KEY_COUNTRY_TITLE + " TEXT,"
            + KEY_COUNTRY_POINT_LONGITUDE + " INTEGER,"
            + KEY_COUNTRY_POINT_LATITUDE + " INTEGER,"
            + KEY_COUNTRY_DISTRICT_TITLE + " TEXT,"
            + KEY_COUNTRY_CITY_STATIONS + " TEXT,"
            + KEY_CITY_FROM_OR_TO + " TEXT" + ")";

    private final SqlStatementValidation sqlStatementValidation = new SqlStatementValidation();

    @Inject
    public TableCity() {
    }

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CITY);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TableCity.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CITIES);
        onCreate(database);
    }

    public void add(City city, SQLiteOpenHelper helper){
        String sql = "INSERT OR REPLACE INTO " + TABLE_CITIES +
                " ( " + KEY_COUNTRY_CITY_ID +
                ", " + KEY_COUNTRY_CITY_TITLE +
                ", " + KEY_COUNTRY_REGION_TITLE +
                ", " + KEY_COUNTRY_TITLE +
                ", " + KEY_COUNTRY_POINT_LONGITUDE +
                ", " + KEY_COUNTRY_POINT_LATITUDE +
                ", " + KEY_COUNTRY_DISTRICT_TITLE +
                ", " + KEY_COUNTRY_CITY_STATIONS +
                ", " + KEY_CITY_FROM_OR_TO +
                " ) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        final SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransactionNonExclusive();
        sqlStatementValidation.runSqlStatementDependingByApiVersion(database, sql, city);
    }

    public void add(Iterable<City> iterable, SQLiteOpenHelper helper){
        for(City city : iterable){
            add(city, helper);
        }
    }

    public void update(City city){
    }

    public void remove(City city){
    }

    public int count(SQLiteOpenHelper helper){
        int num;
        final SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String QUERY = "SELECT  * FROM " + TABLE_CITIES;
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
