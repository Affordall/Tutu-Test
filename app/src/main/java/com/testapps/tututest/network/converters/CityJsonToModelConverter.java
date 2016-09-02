package com.testapps.tututest.network.converters;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.network.ICityJsonToModelListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CityJsonToModelConverter {

    public CityJsonToModelConverter() {
    }

    private long getCountryCityIdResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getLong(INamesTable.City.CITY_ID);
    }

    private String getCountryCityTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.CITY_TITLE);
    }

    private String getCountryRegionTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.REGION_TITLE);
    }

    private String getCountryTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.COUNTY_TITLE);
    }

    private double getCountryPointLongitudeResult(JSONObject jsonObjectItem) throws JSONException {
        JSONObject pointObj = jsonObjectItem.getJSONObject(INamesTable.POINT);
        return pointObj.getDouble(INamesTable.LONGITUDE);
    }

    private double getCountryPointLatitudeResult(JSONObject jsonObjectItem) throws JSONException {
        JSONObject pointObj = jsonObjectItem.getJSONObject(INamesTable.POINT);
        return pointObj.getDouble(INamesTable.LATITUDE);
    }

    private String getCountryDistrictTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.optString(INamesTable.City.DISTRICT_TITLE);
    }

    private List<?> getStationsResult(JSONObject jsonObjectItem) throws JSONException {
        JSONArray arrayStations = jsonObjectItem.getJSONArray(INamesTable.STATIONS);
        List<String> stationsList = new ArrayList<>();
        for (int jsonArrayIndex = 0; jsonArrayIndex < arrayStations.length(); jsonArrayIndex++) {
            JSONObject arrayStationJObj = arrayStations.getJSONObject(jsonArrayIndex);
            stationsList.add(arrayStationJObj.optString(INamesTable.Station.STATION_ID));
        }
        return stationsList;
    }

    /**
     * Interface callback to encapsulate class methods.
     * */

    private ICityJsonToModelListener mCallbackJsonCityToModelConverter = new ICityJsonToModelListener() {

        @Override
        public long getCountryCityId(JSONObject jsonObjectItem) throws JSONException {
            return getCountryCityIdResult(jsonObjectItem);
        }

        @Override
        public String getCountryCityTitle(JSONObject jsonObjectItem) throws JSONException {
            return getCountryCityTitleResult(jsonObjectItem);
        }

        @Override
        public String getCountryRegionTitle(JSONObject jsonObjectItem) throws JSONException {
            return getCountryRegionTitleResult(jsonObjectItem);
        }

        @Override
        public String getCountryTitle(JSONObject jsonObjectItem) throws JSONException {
            return getCountryTitleResult(jsonObjectItem);
        }

        @Override
        public double getCountryPointLongitude(JSONObject jsonObjectItem) throws JSONException {
            return getCountryPointLongitudeResult(jsonObjectItem);
        }

        @Override
        public double getCountryPointLatitude(JSONObject jsonObjectItem) throws JSONException {
            return getCountryPointLatitudeResult(jsonObjectItem);
        }


        @Override
        public String getCountryDistrictTitle(JSONObject jsonObjectItem) throws JSONException {
            return getCountryDistrictTitleResult(jsonObjectItem);
        }

        @Override
        public List<?> getStations(JSONObject jsonObjectItem) throws JSONException {
            return getStationsResult(jsonObjectItem);
        }

        @Override
        public String getFromOtTo(JSONObject jsonObjectItem) throws JSONException {
            return null;
        }
    };

    public ICityJsonToModelListener getmCallbackJsonCityToModelConverter() {
        return mCallbackJsonCityToModelConverter;
    }

}
