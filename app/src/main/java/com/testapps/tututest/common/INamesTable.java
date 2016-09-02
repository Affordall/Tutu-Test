package com.testapps.tututest.common;

/**
 * Created by affy on 29.08.16.
 */
public interface INamesTable {

    String ARRAY_CITIES_FROM = "citiesFrom";
    String ARRAY_CITIES_TO = "citiesTo";

    String CITY = "city";
    String POINT = "point";
    String LONGITUDE = "longitude";
    String LATITUDE = "latitude";
    String STATIONS = "stations";

    interface City {
        String COUNTY_TITLE = "countryTitle";
        String DISTRICT_TITLE = "districtTitle";
        String CITY_ID = "cityId";
        String CITY_TITLE = "cityTitle";
        String REGION_TITLE = "regionTitle";
        String FROM_OR_TO = "fromOrTo";
    }

    interface Station {
        String STATION_ID = "stationId";
        String STATION_TITLE = "stationTitle";
    }
}
