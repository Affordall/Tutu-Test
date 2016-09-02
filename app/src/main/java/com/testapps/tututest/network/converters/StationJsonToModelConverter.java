package com.testapps.tututest.network.converters;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.network.IStationJsonToModelListener;

import org.json.JSONException;
import org.json.JSONObject;

public class StationJsonToModelConverter {

    public StationJsonToModelConverter() {
    }

    private long getStationIdResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getLong(INamesTable.Station.STATION_ID);
    }

    private String getStationTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.Station.STATION_TITLE);
    }

    private String getStationCountryTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.COUNTY_TITLE);
    }

    private double getStationPointLongitudeResult(JSONObject jsonObjectItem) throws JSONException {
        JSONObject pointObj = jsonObjectItem.getJSONObject(INamesTable.POINT);
        return pointObj.getDouble(INamesTable.LONGITUDE);
    }

    private double getStationPointLatitudeResult(JSONObject jsonObjectItem) throws JSONException {
        JSONObject pointObj = jsonObjectItem.getJSONObject(INamesTable.POINT);
        return pointObj.getDouble(INamesTable.LATITUDE);
    }

    private String getStationDistrictTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.DISTRICT_TITLE);
    }

    private long getStationCityIdResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getLong(INamesTable.City.CITY_ID);
    }

    private String getStationCityTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.CITY_TITLE);
    }

    private String getStationRegionTitleResult(JSONObject jsonObjectItem) throws JSONException {
        return jsonObjectItem.getString(INamesTable.City.REGION_TITLE);
    }

    /**
     * Interface callback to encapsulate class methods.
     * */

    private IStationJsonToModelListener mCallbackJsonStationToModelConverter = new IStationJsonToModelListener() {
        @Override
        public long getStationId(JSONObject jsonObjectItem) throws JSONException {
            return getStationIdResult(jsonObjectItem);
        }

        @Override
        public String getStationTitle(JSONObject jsonObjectItem) throws JSONException {
            return getStationTitleResult(jsonObjectItem);
        }

        @Override
        public String getStationCountryTitle(JSONObject jsonObjectItem) throws JSONException {
            return getStationCountryTitleResult(jsonObjectItem);
        }

        @Override
        public double getStationPointLongitude(JSONObject jsonObjectItem) throws JSONException {
            return getStationPointLongitudeResult(jsonObjectItem);
        }

        @Override
        public double getStationPointLatitude(JSONObject jsonObjectItem) throws JSONException {
            return getStationPointLatitudeResult(jsonObjectItem);
        }

        @Override
        public String getStationDistrictTitle(JSONObject jsonObjectItem) throws JSONException {
            return getStationDistrictTitleResult(jsonObjectItem);
        }

        @Override
        public long getStationCityId(JSONObject jsonObjectItem) throws JSONException {
            return getStationCityIdResult(jsonObjectItem);
        }

        @Override
        public String getStationCityTitle(JSONObject jsonObjectItem) throws JSONException {
            return getStationCityTitleResult(jsonObjectItem);
        }

        @Override
        public String getStationRegionTitle(JSONObject jsonObjectItem) throws JSONException {
            return getStationRegionTitleResult(jsonObjectItem);
        }
    };

    public IStationJsonToModelListener getmCallbackJsonStationToModelConverter() {
        return mCallbackJsonStationToModelConverter;
    }

}
