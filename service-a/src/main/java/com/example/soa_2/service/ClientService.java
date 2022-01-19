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
        return businessService.findAllDirectors(Pageable.DEFAULT)
                              .getContent()
                              .stream()
                              .filter(director -> {
                                  List<MovieDto> content = businessService.findMoviesByDirectorId(director.getId(), Pageable.DEFAULT)
                                                                          .getContent();
                                  if (!content.isEmpty())
                                      return content.stream()
                                                    .map(MovieDto::getOscarsCount)
                                                    .reduce(0L, Long::sum) == 0;
                                  return false;
                              })
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
                                                                        m.setOscarsCount(m.getOscarsCount() != 0 ? 0 : m.getOscarsCount());
                                                                        return businessService.updateMovie(m.getId(), m);
                                                                    })
                              ).collect(Collectors.toList());
    }
}
