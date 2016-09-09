package com.testapps.tututest.specifications.impl;

import com.testapps.tututest.database.tables.TableCity;
import com.testapps.tututest.specifications.ISqlSpecification;

/**
 * Created by affy on 08.09.16.
 */
public class SearchSpecification implements ISqlSpecification {

    private String mSearch;

    public SearchSpecification(String wordsForSearch) {
        this.mSearch = wordsForSearch;
    }

    @Override
    public String toSqlQuery() {
        return getFinalQuery();
    }

    private String getSelectQuery() {
        return "SELECT  * FROM "
                + TableCity.TABLE_CITIES
                + " WHERE " + TableCity.KEY_COUNTRY_CITY_TITLE
                + " LIKE " + "(cast(" + "%cns" +" as text))";
    }

    private String getFinalQuery() {

        String selectQuery = getSelectQuery();

        StringBuilder cns = new StringBuilder();
        cns.append("'%$");
        cns.append(mSearch);
        cns.append("%'");
        String finalQuery = null;
        try {
            finalQuery = selectQuery.replaceAll("%cns", cns.toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return finalQuery;
    }

}
