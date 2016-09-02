package com.testapps.tututest.specifications.impl;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.specifications.ISqlSpecification;

/**
 * Created by affy on 01.09.16.
 */
public class AllCitiesFromSortedListSpecification implements ISqlSpecification {

    private static final String ORDER_BY_DESCEND = " DESC";
    private static final String ORDER_BY_ASCEND = " ASC";

    @Override
    public String toSqlQuery() {
        return "SELECT  * FROM "
                + TableCity.TABLE_CITIES
                + " WHERE " + TableCity.KEY_CITY_FROM_OR_TO
                + "=" + "'" + INamesTable.ARRAY_CITIES_FROM + "'"
                + " ORDER BY "+ TableCity.KEY_COUNTRY_CITY_TITLE
                + ORDER_BY_ASCEND
                + ";";
    }
}

