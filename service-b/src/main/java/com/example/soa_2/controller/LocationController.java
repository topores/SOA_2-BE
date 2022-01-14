package com.example.soa_2.controller;

import com.example.soa_2.model.Location;
import com.example.soa_2.service.LocationService;
import dto.LocationDto;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/location")
public class LocationController extends AbstractCrudController<LocationDto, Location> {
    @Inject
    public LocationController(LocationService service) {
        super(service);
    }

}
