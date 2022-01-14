package com.example.soa_2.controller;

import com.example.soa_2.model.MovieGenre;
import com.example.soa_2.service.MovieService;
import com.example.soa_2.service.PersonService;
import dto.CountDto;
import dto.PersonDto;
import paging.Page;
import paging.Pageable;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("additional")
public class AdditionalTaskController {

    @Inject
    private MovieService movieService;

    @Inject
    private PersonService personService;

    @GET
    @Path("/count-by-oscars")
    public CountDto countMoviesByOscarsLessThen(@QueryParam("amount") Long amount) {
        return movieService.countMoviesByOscarsLessThen(amount);
    }

    @GET
    @Path("/count-by-genre")
    public CountDto countMoviesByGenre(@QueryParam("genre") MovieGenre genre) {
        return movieService.countMoviesByGenre(genre);
    }

    @GET
    @Path("/person-by-director")
    public Page<PersonDto> findAllByDirector(@QueryParam("name") String director,
                                             @BeanParam Pageable pageable) {
        return Page.of(personService.findAllByDirectorLessThen(director), pageable);
    }
}
