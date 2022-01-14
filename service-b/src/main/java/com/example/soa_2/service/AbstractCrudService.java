package com.example.soa_2.service;

import com.example.soa_2.mapper.BasicEntityDtoMapper;
import com.example.soa_2.model.IdableClass;
import com.example.soa_2.repository.AbstractCrudRepository;

import java.util.List;

public abstract class AbstractCrudService<DTO, ENTITY> extends ServiceClassConfiguration {
    protected AbstractCrudRepository<ENTITY> repository;
    protected BasicEntityDtoMapper<DTO, ENTITY> mapper;

    public AbstractCrudService(AbstractCrudRepository<ENTITY> repository,
                               BasicEntityDtoMapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AbstractCrudService() {
    }

    public List<DTO> getAll() {
        return mapper.mapEntitiesToDtos(repository.findAll());
    }

    public DTO getById(Integer id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public DTO create(DTO createDto) {
        return mapper.mapToDto(repository.create(mapper.mapToEntity(createDto)));
    }

    public DTO update(Integer id, DTO updateDto) {
        ENTITY updateEntity = mapper.mapToEntity(updateDto);

        if (classRegistry.contains(updateEntity.getClass()))
            ((IdableClass) updateEntity).setId(id);

        return mapper.mapToDto(repository.update(id, updateEntity));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}


