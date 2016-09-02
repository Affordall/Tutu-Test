package com.testapps.tututest.database;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.text.TextUtils;

import com.testapps.tututest.models.City;
import com.testapps.tututest.models.Station;
import com.testapps.tututest.utils.Utils;

import org.jetbrains.annotations.Contract;

/**
 * Created by affy on 01.09.16.
 */
public class SqlStatementValidation {

    private int deviceAPI = Build.VERSION.SDK_INT;

    public SqlStatementValidation() {}

    public void runSqlStatementDependingByApiVersion(SQLiteDatabase db, String sql, Object incomeObj) {
        if (isKitkat()) {
            newApiSqlStatement(db, sql, incomeObj);
        } else {
            oldApiSqlStatement(db, sql, incomeObj);
        }
    }

    public void oldApiSqlStatement(SQLiteDatabase db, String sql, Object incomeObj) {
        SQLiteStatement stmt = db.compileStatement(sql);
        try {
            validateIncomingInstance(db, stmt, incomeObj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            stmt.close();
            closeDB(db);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void newApiSqlStatement(SQLiteDatabase db, String sql, Object incomeObj) {
        try (SQLiteStatement stmt = db.compileStatement(sql)) {
            validateIncomingInstance(db, stmt, incomeObj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            closeDB(db);
        }
    }

    public void validateIncomingInstance(SQLiteDatabase db, SQLiteStatement stmt, Object incomeObj) throws Exception {
        if (incomeObj instanceof City) {
            commonSqlStatementCity(db, stmt, (City) incomeObj);
        } else if (incomeObj instanceof Station) {
            commonSqlStatementStation(db, stmt, (Station)incomeObj);
        } else {
            throw new Exception("Unknown incoming instance of class");
        }
    }

    public void safeStmt(SQLiteStatement stmt, int index, String incString) {
        if (incString != null) {
            stmt.bindString(index, incString);
        } else {
            stmt.bindNull(index);
        }
    }

    public void safeStmt(SQLiteStatement stmt, int index, long incLong) {
        String referrer = Long.toString(incLong);
        if (referrer != null) {
            stmt.bindLong(index, incLong);
        } else {
            stmt.bindNull(index);
        }
    }

    public void safeStmt(SQLiteStatement stmt, int index, double incDouble) {
        String referrer = Double.toString(incDouble);
        if (referrer != null) {
            stmt.bindDouble(index, incDouble);
        } else {
            stmt.bindNull(index);
        }
    }

    private void closeDB(SQLiteDatabase db) {
        if (db != null && db.isOpen())
            db.close();
    }

    public void commonSqlStatementCity(SQLiteDatabase db, SQLiteStatement stmt, City city) {
        try {
            safeStmt(stmt, 1, city.getCountryCityId());
            safeStmt(stmt, 2, city.getCountryCityTitle());
            safeStmt(stmt, 3, city.getCountryRegionTitle());
            safeStmt(stmt, 4, city.getCountryTitle());
            safeStmt(stmt, 5, city.getCountryPointLongitude());
            safeStmt(stmt, 6, city.getCountryPointLatitude());
            safeStmt(stmt, 7, city.getCountryDistrictTitle());
            safeStmt(stmt, 8, TextUtils.join(", ", city.getStations()));
            safeStmt(stmt, 9, city.getFromOrTo());

            stmt.execute();
            stmt.clearBindings();

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void commonSqlStatementStation(SQLiteDatabase db, SQLiteStatement stmt, Station station) {
        try {
            safeStmt(stmt, 1, station.getStationId());
            safeStmt(stmt, 2, station.getStationTitle());
            safeStmt(stmt, 3, station.getStationCountryTitle());
            safeStmt(stmt, 4, station.getStationPointLongitude());
            safeStmt(stmt, 5, station.getStationPointLatitude());
            safeStmt(stmt, 6, station.getStationDistrictTitle());
            safeStmt(stmt, 7, station.getStationCityId());
            safeStmt(stmt, 8, station.getStationCityTitle());
            safeStmt(stmt, 9, station.getStationRegionTitle());

            stmt.execute();
            stmt.clearBindings();

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public boolean isKitkat() {
        return deviceAPI >= Build.VERSION_CODES.KITKAT;
    }

}
