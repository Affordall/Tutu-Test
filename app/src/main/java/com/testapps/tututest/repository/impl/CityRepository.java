package com.testapps.tututest.repository.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testapps.tututest.database.mapper.CursorToCityMapper;
import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.database.mapper.IBaseMapper;
import com.testapps.tututest.models.City;
import com.testapps.tututest.repository.IRepository;
import com.testapps.tututest.specifications.ISpecification;
import com.testapps.tututest.specifications.ISqlSpecification;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CityRepository implements IRepository<City> {

    private final IBaseMapper<Cursor, City> toCityMapper;
    private DatabaseHandler handler;

    @Inject
    public CityRepository(DatabaseHandler handler) {
        this.handler = handler;
        this.toCityMapper = new CursorToCityMapper();
    }

    @Override
    public void add(City item) {
        handler.addCity(item);
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
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        final SQLiteDatabase database = handler.getReadableDatabase();
        final List<City> cities = new ArrayList<>();
        try {
            final Cursor cursor = database.rawQuery(sqlSpecification.toSqlQuery(), new String[]{});
            for (int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);
                cities.add(toCityMapper.map(cursor));
            }
            cursor.close();
            return cities;
        } finally {
            database.close();
        }
    }
}
