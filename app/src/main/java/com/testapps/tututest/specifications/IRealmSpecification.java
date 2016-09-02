package com.testapps.tututest.specifications;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by affy on 02.09.16.
 */
public interface IRealmSpecification extends ISpecification {
    RealmResults<?> toRealmResults(Realm realm);
}