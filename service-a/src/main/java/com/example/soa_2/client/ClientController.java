package com.example.soa_2.client;

import com.example.soa_2.service.ClientService;
import dto.MovieDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@RequestScoped
@Path("")
public class ClientController {

    @Inject
    private ClientService clientService;

    @GET
    @Path("/screenwriters/losers")
    public Page<PersonDto> findLosers(@BeanParam Pageable pageable) {
        return Page.of(clientService.findLosers(), pageable);
    }

    @PUT
    @Path("/directors/humiliate-by-genre/{genre}")
    public Page<MovieDto> humiliation(@PathParam("genre") String genre,
                                      @BeanParam Pageable pageable) {
        return Page.of(clientService.humiliate(genre), pageable);
    }
}
