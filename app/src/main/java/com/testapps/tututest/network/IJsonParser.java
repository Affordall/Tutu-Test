package com.testapps.tututest.network;

import com.testapps.tututest.models.City;
import com.testapps.tututest.specifications.ISpecification;
import com.testapps.tututest.specifications.ISqlSpecification;

import java.util.ArrayList;

/**
 * Created by affy on 01.09.16.
 */
public interface IJsonParser {
    ArrayList<City> getListCityFrom(ISpecification specification);
    ArrayList<City> getListCityTo(ISpecification specification);
}
