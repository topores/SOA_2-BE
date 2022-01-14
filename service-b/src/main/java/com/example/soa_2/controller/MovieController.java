package com.example.soa_2.controller;

import com.example.soa_2.model.Movie;
import com.example.soa_2.service.MovieService;
import dto.MovieDto;
import paging.Page;
import paging.Pageable;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/movie")
public class MovieController extends AbstractCrudController<MovieDto, Movie> {

    @Inject
    public MovieController(MovieService service) {
        super(service);
    }

    @GET
    @Path("/by-genre/{genre}")
    public Page<MovieDto> findMoviesByGenre(@PathParam("genre") String genre,
                                            @BeanParam Pageable pageable) {
        return Page.of(((MovieService) service).findAllByGenre(genre), pageable);
    }

    @GET
    @Path("/by-director/{directorId}")
    public Page<MovieDto> findMoviesByDirectorId(@PathParam("directorId") Integer id,
                                                 @BeanParam Pageable pageable) {
        return Page.of(((MovieService) service).findAllByDirectorId(id), pageable);
    }

}
