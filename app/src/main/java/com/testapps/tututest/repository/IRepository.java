package com.testapps.tututest.repository;

import com.testapps.tututest.specifications.ISpecification;

import java.util.List;

public interface IRepository<T> {
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    void remove(ISpecification specification);

    List<T> query(ISpecification specification);
}
