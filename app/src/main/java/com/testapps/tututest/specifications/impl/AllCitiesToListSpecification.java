package com.testapps.tututest.specifications.impl;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.specifications.ISqlSpecification;

/**
 * Created by affy on 02.09.16.
 */
public class AllCitiesToListSpecification implements ISqlSpecification {

    @Override
    public String toSqlQuery() {
        return "SELECT  * FROM "
                + TableCity.TABLE_CITIES
                + " WHERE " + TableCity.KEY_CITY_FROM_OR_TO
                + "=" + "'" + INamesTable.ARRAY_CITIES_TO + "'"
                + ";";
    }

}
