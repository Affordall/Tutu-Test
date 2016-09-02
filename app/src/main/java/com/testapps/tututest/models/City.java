package com.testapps.tututest.models;

import java.util.List;

/**
 * Created by affy on 29.08.16.
 */
public class City {

    private long countryCityId;
    private String countryCityTitle;
    private String countryRegionTitle;
    private String countryTitle;
    private double countryPointLongitude;
    private double countryPointLatitude;
    private String countryDistrictTitle;
    private List<?> stations;
    private String fromOrTo;

    public City(){}

    public long getCountryCityId() {
        return countryCityId;
    }

    public void setCountryCityId(long countryCityId) {
        this.countryCityId = countryCityId;
    }

    public String getCountryCityTitle() {
        return countryCityTitle;
    }

    public void setCountryCityTitle(String countryCityTitle) {
        this.countryCityTitle = countryCityTitle;
    }

    public String getCountryRegionTitle() {
        return countryRegionTitle;
    }

    public void setCountryRegionTitle(String countryRegionTitle) {
        this.countryRegionTitle = countryRegionTitle;
    }

    public String getCountryTitle() {
        return countryTitle;
    }

    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }

    public double getCountryPointLongitude() {
        return countryPointLongitude;
    }

    public void setCountryPointLongitude(double countryPointLongitude) {
        this.countryPointLongitude = countryPointLongitude;
    }

    public double getCountryPointLatitude() {
        return countryPointLatitude;
    }

    public void setCountryPointLatitude(double countryPointLatitude) {
        this.countryPointLatitude = countryPointLatitude;
    }

    public String getCountryDistrictTitle() {
        return countryDistrictTitle;
    }

    public void setCountryDistrictTitle(String countryDistrictTitle) {
        this.countryDistrictTitle = countryDistrictTitle;
    }

    public List<?> getStations() {
        return stations;
    }

    public void setStations(List<?> stations) {
        this.stations = stations;
    }

    public String getFromOrTo() {
        return fromOrTo;
    }

    public void setFromOrTo(String fromOrTo) {
        this.fromOrTo = fromOrTo;
    }
}
