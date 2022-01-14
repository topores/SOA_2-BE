package com.example.soa_2.service;

import com.example.soa_2.mapper.LocationMapper;
import com.example.soa_2.model.Location;
import com.example.soa_2.repository.LocationRepository;
import dto.LocationDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class LocationService extends AbstractCrudService<LocationDto, Location> {

    @Inject
    public LocationService(LocationRepository repository,
                           LocationMapper mapper) {
        super(repository, mapper);
    }

    public LocationService() {
    }
}
