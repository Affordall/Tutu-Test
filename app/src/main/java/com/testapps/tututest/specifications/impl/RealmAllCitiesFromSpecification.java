package com.testapps.tututest.specifications.impl;

import com.testapps.tututest.common.INamesTable;
import com.testapps.tututest.specifications.IRealmSpecification;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by affy on 01.09.16.
 */
public class RealmAllCitiesFromSpecification implements IRealmSpecification {

    @Override
    public RealmResults<?> toRealmResults(Realm realm) {
        return null;
//        return realm.where(CityRealm.class)
//                .equalTo(INamesTable.City.FROM_OR_TO, INamesTable.ARRAY_CITIES_FROM)
//                .findAll();
    }
}
