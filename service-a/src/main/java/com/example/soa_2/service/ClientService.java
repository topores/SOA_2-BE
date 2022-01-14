package com.example.soa_2.service;

import dto.MovieDto;
import dto.PersonDto;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import paging.Pageable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ClientService {
    @Inject
    @RestClient
    private BusinessService businessService;

    public List<PersonDto> findLosers() {
        return businessService.findAllMovies(Pageable.DEFAULT)
                              .getContent()
                              .stream()
                              .filter(m -> m.getOscarsCount() == 0)
                              .map(m -> businessService.findDirectorById(m.getDirectorId()))
                              .collect(Collectors.toList());
    }

    public List<MovieDto> humiliate(String genre) {
        return businessService.findMoviesByGenre(genre, Pageable.DEFAULT)
                              .getContent()
                              .stream()
                              .map(MovieDto::getDirectorId)
                              .flatMap(directorId -> businessService.findMoviesByDirectorId(directorId, Pageable.DEFAULT)
                                                                    .getContent()
                                                                    .stream()
                                                                    .map(m -> {
                                                                        m.setOscarsCount(m.getOscarsCount() != 0 ? m.getOscarsCount() - 1 : m.getOscarsCount());
                                                                        return businessService.updateMovie(m.getId(), m);
                                                                    })
                              ).collect(Collectors.toList());
    }
}
