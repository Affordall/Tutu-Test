package com.testapps.tututest.repository.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testapps.tututest.database.DatabaseHandler;
import com.testapps.tututest.database.mapper.CursorToStationMapper;
import com.testapps.tututest.database.mapper.IBaseMapper;
import com.testapps.tututest.models.Station;
import com.testapps.tututest.repository.IRepository;
import com.testapps.tututest.specifications.ISpecification;
import com.testapps.tututest.specifications.ISqlSpecification;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StationRepository implements IRepository<Station> {

    private final IBaseMapper<Cursor, Station> toStationMapper;
    private final DatabaseHandler handler;

    @Inject
    public StationRepository(DatabaseHandler handler) {
        this.handler = handler;
        this.toStationMapper = new CursorToStationMapper();
    }

    @Override
    public void add(Station item) {
        handler.addStation(item);
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
        final ISqlSpecification sqlSpecification = (ISqlSpecification) specification;
        final SQLiteDatabase database = handler.getReadableDatabase();
        final List<Station> stations = new ArrayList<>();
        try {
            final Cursor cursor = database.rawQuery(sqlSpecification.toSqlQuery(), new String[]{});
            for (int i = 0, size = cursor.getCount(); i < size; i++) {
                cursor.moveToPosition(i);
                stations.add(toStationMapper.map(cursor));
            }
            cursor.close();
            return stations;
        } finally {
            database.close();
        }
    }
}
