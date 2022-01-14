package com.example.soa_2.controller;

import com.example.soa_2.service.AbstractCrudService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import paging.Page;
import paging.Pageable;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public abstract class AbstractCrudController<DTO, ENTITY> {
    protected final AbstractCrudService<DTO, ENTITY> service;

    @Inject
    public AbstractCrudController(AbstractCrudService<DTO, ENTITY> service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public DTO getById(@PathParam("id") Integer id) {
        return service.getById(id);
    }

    @PUT
    @Path("/{id}")
    public DTO update(@PathParam("id") Integer id, @RequestBody DTO updateDto) {
        return service.update(id, updateDto);
    }

    @POST
    public DTO create(@RequestBody DTO createDto) {
        return service.create(createDto);
    }

    @GET
    public Page<DTO> getAll(@BeanParam Pageable pageable) {
        return Page.of(service.getAll(), pageable);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        service.deleteById(id);
    }
}
