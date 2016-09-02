package com.testapps.tututest.database.mapper;

public interface IBaseMapper<From, To> {
    To map(From from);
}
