package com.example.soa_2.service;

import com.example.soa_2.mapper.CoordinatesMapper;
import com.example.soa_2.model.Coordinates;
import com.example.soa_2.repository.CoordinatesRepository;
import dto.CoordinatesDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class CoordinatesService extends AbstractCrudService<CoordinatesDto, Coordinates> {

    @Inject
    public CoordinatesService(CoordinatesRepository repository,
                              CoordinatesMapper mapper) {
        super(repository, mapper);
    }

    public CoordinatesService() {
    }
}
