package com.example.soa_2.service;

import com.example.soa_2.model.Coordinates;
import com.example.soa_2.model.IdableClass;
import com.example.soa_2.model.Movie;
import com.example.soa_2.model.Person;

import java.util.List;

public class ServiceClassConfiguration {
    protected final List<Class<? extends IdableClass>> classRegistry = List.of(
            Person.class,
            Coordinates.class,
            Movie.class
    );
}
