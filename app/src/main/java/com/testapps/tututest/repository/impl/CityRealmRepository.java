package com.testapps.tututest.repository.impl;

import com.testapps.tututest.models.City;
import com.testapps.tututest.repository.IRepository;
import com.testapps.tututest.specifications.IRealmSpecification;
import com.testapps.tututest.specifications.ISpecification;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by affy on 02.09.16.
 */
@SuppressWarnings("unused")
public class CityRealmRepository implements IRepository<City> {

    //private final IBaseMapper<CityRealm, City> toCityMapper;

    public CityRealmRepository() {
        //this.toCityMapper = new CityRealmToCityMapper();
    }

    @Override
    public void add(final City item) {
//        final Realm realm = Realm.getInstance(realmConfiguration);
//
//        realm.executeTransaction(realm1 -> {
//            final CityRealm cityRealm = realm1.createObject(CityRealm.class);
//            // setters
//        });
//
//        realm.close();
    }

    @Override
    public void add(Iterable<City> items) {

    }

    @Override
    public void update(City item) {

    }

    @Override
    public void remove(City item) {

    }

    @Override
    public void remove(ISpecification specification) {

    }

    @Override
    public List<City> query(ISpecification specification) {
//        final IRealmSpecification realmSpecification = (IRealmSpecification) specification;
//
//        final Realm realm = Realm.getInstance(realmConfiguration);
//        final RealmResults<CityRealm> realmResults = realmSpecification.toRealmResults(realm);
//
//        final List<City> cities = new ArrayList<>();
//
//        for (CityRealm cityRealm : realmResults) {
//            cities.add(toCityMapper.map(cityRealm));
//        }
//
//        realm.close();
//
//        return cities;
        return null;
    }
}
