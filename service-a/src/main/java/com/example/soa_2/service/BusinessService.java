package com.example.soa_2.service;

import dto.MovieDto;
import dto.PersonDto;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import paging.Page;
import paging.Pageable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient
@ApplicationScoped
public interface BusinessService {

    @GET
    @Path("/person")
    Page<PersonDto> findAllDirectors(@BeanParam Pageable pageable);

    @GET
    @Path("/person/{id}")
    PersonDto findDirectorById(@PathParam("id") Integer id);

    @GET
    @Path("/movie")
    Page<MovieDto> findAllMovies(@BeanParam Pageable pageable);

    @GET
    @Path("/movie/by-genre/{genre}")
    Page<MovieDto> findMoviesByGenre(@PathParam("genre") String genre,
                                     @BeanParam Pageable pageable);

    @GET
    @Path("/movie/by-director/{directorId}")
    Page<MovieDto> findMoviesByDirectorId(@PathParam("directorId") Integer id,
                                          @BeanParam Pageable pageable);

    @PUT
    @Path("/movie/{id}")
    MovieDto updateMovie(@PathParam("id") Integer id, @RequestBody MovieDto updateDto);
}
