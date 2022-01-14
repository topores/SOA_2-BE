package com.example.soa_2.service;

import com.example.soa_2.mapper.MovieMapper;
import com.example.soa_2.model.Movie;
import com.example.soa_2.model.MovieGenre;
import com.example.soa_2.repository.MovieRepository;
import dto.CountDto;
import dto.MovieDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class MovieService extends AbstractCrudService<MovieDto, Movie> {

    @Inject
    public MovieService(MovieRepository repository,
                        MovieMapper mapper) {
        super(repository, mapper);
    }

    public MovieService() {
    }

    public CountDto countMoviesByOscarsLessThen(long amount) {
        return CountDto.builder()
                       .count(((MovieRepository) repository).countMoviesByOscarsLessThen(amount))
                       .build();
    }

    public CountDto countMoviesByGenre(MovieGenre genre) {
        return CountDto.builder()
                       .count(((MovieRepository) repository).countMoviesByGenre(genre))
                       .build();
    }

    public List<MovieDto> findAllByGenre(String genre) {
        return mapper.mapEntitiesToDtos(((MovieRepository) repository).findAllByGenre(genre));
    }

    public List<MovieDto> findAllByDirectorId(Integer id) {
        return mapper.mapEntitiesToDtos(((MovieRepository) repository).findAllByDirectorId(id));
    }
}
