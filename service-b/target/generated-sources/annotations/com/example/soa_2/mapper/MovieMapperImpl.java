package com.example.soa_2.mapper;

import com.example.soa_2.model.Movie;
import com.example.soa_2.model.MovieGenre;
import com.example.soa_2.model.MpaaRating;
import dto.MovieDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-19T15:57:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Azul Systems, Inc.)"
)
@ApplicationScoped
public class MovieMapperImpl extends MovieMapper {

    @Override
    public MovieDto mapToDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        if ( movie.getMpaaRating() != null ) {
            movieDto.setRating( movie.getMpaaRating().name() );
        }
        movieDto.setId( movie.getId() );
        movieDto.setName( movie.getName() );
        movieDto.setOscarsCount( movie.getOscarsCount() );
        if ( movie.getGenre() != null ) {
            movieDto.setGenre( movie.getGenre().name() );
        }

        afterMapToDto( movieDto, movie );

        return movieDto;
    }

    @Override
    public Movie mapToEntity(MovieDto movieDto) {
        if ( movieDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        if ( movieDto.getRating() != null ) {
            movie.setMpaaRating( Enum.valueOf( MpaaRating.class, movieDto.getRating() ) );
        }
        movie.setId( movieDto.getId() );
        movie.setName( movieDto.getName() );
        movie.setOscarsCount( movieDto.getOscarsCount() );
        if ( movieDto.getGenre() != null ) {
            movie.setGenre( Enum.valueOf( MovieGenre.class, movieDto.getGenre() ) );
        }

        afterMapToEntity( movie, movieDto );

        return movie;
    }

    @Override
    public List<MovieDto> mapEntitiesToDtos(List<Movie> movies) {
        if ( movies == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>( movies.size() );
        for ( Movie movie : movies ) {
            list.add( mapToDto( movie ) );
        }

        return list;
    }

    @Override
    public List<Movie> mapDtosToEntities(List<MovieDto> movieDtos) {
        if ( movieDtos == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<Movie>( movieDtos.size() );
        for ( MovieDto movieDto : movieDtos ) {
            list.add( mapToEntity( movieDto ) );
        }

        return list;
    }
}
