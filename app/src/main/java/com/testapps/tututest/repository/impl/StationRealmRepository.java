package com.testapps.tututest.repository.impl;

import com.testapps.tututest.models.Station;
import com.testapps.tututest.repository.IRepository;
import com.testapps.tututest.specifications.IRealmSpecification;
import com.testapps.tututest.specifications.ISpecification;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by affy on 02.09.16.
 */
@SuppressWarnings("unused")
public class StationRealmRepository implements IRepository<Station> {

    //private final IBaseMapper<StationRealm, Station> toStationMapper;

    public StationRealmRepository() {
        //this.toStationMapper = new StationRealmToCityMapper();
    }

    @Override
    public void add(final Station item) {
//        final Realm realm = Realm.getInstance(realmConfiguration);
//
//        realm.executeTransaction(realm1 -> {
//            final StationRealm stationRealm = realm1.createObject(StationRealm.class);
//            stationRealm.setStationId(station.getStationId());
//            stationRealm.setStationTitle(station.getStationTitle());
//            stationRealm.setStationCountryTitle(station.getStationCountryTitle());
//            stationRealm.setStationPointLongitude(station.getStationPointLongitude());
//            stationRealm.setStationPointLatitude(station.getStationPointLatitude());
//            stationRealm.setStationDistrictTitle(station.getStationDistrictTitle());
//            stationRealm.setStationCityId(station.getStationCityId());
//            stationRealm.setStationCityTitle(station.getStationCityTitle());
//            stationRealm.setStationRegionTitle(station.getStationRegionTitle());
//        });
//        realm.close();
    }

    @Override
    public void add(Iterable<Station> items) {

    }

    @Override
    public void update(Station item) {

    }

    @Override
    public void remove(Station item) {

    }

    @Override
    public void remove(ISpecification specification) {

    }

    @Override
    public List<Station> query(ISpecification specification) {
//        final IRealmSpecification realmSpecification = (IRealmSpecification) specification;
//
//        final Realm realm = Realm.getInstance(realmConfiguration);
//        final RealmResults<StationRealm> realmResults = realmSpecification.toRealmResults(realm);
//
//        final List<Station> stations = new ArrayList<>();
//
//        for (StationRealm stationRealm : realmResults) {
//            stations.add(toStationMapper.map(stationRealm));
//        }
//
//        realm.close();
//
//        return stations;
        return null;
    }
}
