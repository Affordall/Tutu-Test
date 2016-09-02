package com.testapps.tututest.database.mapper;

import android.database.Cursor;

import com.testapps.tututest.database.CursorUtils;
import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.models.City;

import java.util.Arrays;
import java.util.List;

/**
 * Created by affy on 01.09.16.
 */
public class CursorToCityMapper implements IBaseMapper<Cursor,City> {

    public City map(Cursor cursor) {
        return createNewCityByCursor(cursor);
    }

    private City createNewCityByCursor(Cursor cursor) {
        City city = new City();
            city.setCountryCityId(getCountryCityIdFromCursor(cursor));
            city.setCountryCityTitle(getCountryCityTitleFromCursor(cursor));
            city.setCountryRegionTitle(getCountryCityRegionTitleFromCursor(cursor));
            city.setCountryTitle(getCountryTitleFromCursor(cursor));
            city.setCountryPointLongitude(getCountryPointLongitudeFromCursor(cursor));
            city.setCountryPointLatitude(getCountryPointLatitudeFromCursor(cursor));
            city.setCountryDistrictTitle(getCountryDistrictTitleFromCursor(cursor));
            city.setStations(getStationsListFromCursor(cursor));
            city.setFromOrTo(getCityFromOrToFromCursor(cursor));
        return city;
    }

    private long getCountryCityIdFromCursor(Cursor cursor) {
        return CursorUtils.getLongCursor(cursor, TableCity.KEY_COUNTRY_CITY_ID);
    }

    private String getCountryCityTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableCity.KEY_COUNTRY_CITY_TITLE);
    }

    private String getCountryCityRegionTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableCity.KEY_COUNTRY_REGION_TITLE);
    }

    private String getCountryTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableCity.KEY_COUNTRY_TITLE);
    }

    private double getCountryPointLongitudeFromCursor(Cursor cursor) {
        return CursorUtils.getDoubleCursor(cursor, TableCity.KEY_COUNTRY_POINT_LONGITUDE);
    }

    private double getCountryPointLatitudeFromCursor(Cursor cursor) {
        return CursorUtils.getDoubleCursor(cursor, TableCity.KEY_COUNTRY_POINT_LATITUDE);
    }

    private String getCountryDistrictTitleFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableCity.KEY_COUNTRY_DISTRICT_TITLE);
    }

    private List<String> getStationsListFromCursor(Cursor cursor) {
        String stationsString = CursorUtils.getStringCursor(cursor, TableCity.KEY_COUNTRY_CITY_STATIONS);
        return Arrays.asList(stationsString.split("\\s*,\\s*"));
    }

    private String getCityFromOrToFromCursor(Cursor cursor) {
        return CursorUtils.getStringCursor(cursor, TableCity.KEY_CITY_FROM_OR_TO);
    }
}
