package com.example.soa_2.repository;

import com.example.soa_2.model.Location;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class LocationRepository extends AbstractCrudRepository<Location> {
    public LocationRepository() {
        super(Location.class);
    }
}
