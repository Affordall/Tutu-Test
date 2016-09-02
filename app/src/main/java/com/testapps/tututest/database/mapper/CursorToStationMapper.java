package com.testapps.tututest.database.mapper;

import android.database.Cursor;

import com.testapps.tututest.database.CursorUtils;
import com.testapps.tututest.database.tables.TableStation;
import com.testapps.tututest.models.Station;

/**
 * Created by affy on 01.09.16.
 */
public class CursorToStationMapper implements IBaseMapper<Cursor,Station> {

    @Override
    public Station map(Cursor cursor) {
        return createNewStationByCursor(cursor);
    }

    private Station createNewStationByCursor(Cursor cursor) {
        Station station = new Station();
            station.setStationId(getStationIdFromCursor(cursor));
            station.setStationTitle(getStationTitleFromCursor(cursor));
            station.setStationCountryTitle(getStationCountryTitleFromCursor(cursor));
            station.setStationPointLongitude(getStationPointLongitudeFromCursor(cursor));
            station.setStationPointLatitude(getStationPointLatitudeFromCursor(cursor));
            station.setStationDistrictTitle(getStationDistrictTitleFromCursor(cursor));
            station.setStationCityId(getStationCityIdFromCursor(cursor));
            station.setStationCityTitle(getStationCityTitleFromCursor(cursor));
            station.setStationRegionTitle(getStationRegionTitleFromCursor(cursor));
        return station;
    }

    private long getStationIdFromCursor(Cursor cursor) {
        return CursorUtils.getLongCursor(cursor, TableStation.KEY_STATION_CITY_ID);
    }

    private String getStationTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableStation.KEY_STATION_CITY_TITLE);
    }

    private String getStationCountryTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableStation.KEY_STATION_COUNTRY_TITLE);
    }

    private double getStationPointLongitudeFromCursor(Cursor cursor) {
        return CursorUtils.getDoubleCursor(cursor, TableStation.KEY_STATION_POINT_LONGITUDE);
    }

    private double getStationPointLatitudeFromCursor(Cursor cursor) {
        return CursorUtils.getDoubleCursor(cursor, TableStation.KEY_STATION_POINT_LATITUDE);
    }

    private String getStationDistrictTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableStation.KEY_STATION_DISTRICT_TITLE);
    }

    private long getStationCityIdFromCursor(Cursor cursor) {
        return CursorUtils.getLongCursor(cursor, TableStation.KEY_STATION_CITY_ID);
    }

    private String getStationCityTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableStation.KEY_STATION_CITY_TITLE);
    }

    private String getStationRegionTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableStation.KEY_STATION_REGION_TITLE);
    }
}
