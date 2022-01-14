package com.example.soa_2.controller;

import com.example.soa_2.model.Coordinates;
import com.example.soa_2.service.CoordinatesService;
import dto.CoordinatesDto;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/coordinates")
public class CoordinatesController extends AbstractCrudController<CoordinatesDto, Coordinates> {

    @Inject
    public CoordinatesController(CoordinatesService service) {
        super(service);
    }

}

