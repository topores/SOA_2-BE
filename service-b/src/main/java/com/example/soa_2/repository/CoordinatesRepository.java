package com.example.soa_2.repository;

import com.example.soa_2.model.Coordinates;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CoordinatesRepository extends AbstractCrudRepository<Coordinates> {
    public CoordinatesRepository() {
        super(Coordinates.class);
    }
}
