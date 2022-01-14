package com.example.soa_2.mapper;

import com.example.soa_2.model.Movie;
import com.example.soa_2.repository.CoordinatesRepository;
import com.example.soa_2.repository.PersonRepository;
import dto.MovieDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Mapper(componentModel = "CDI")
public abstract class MovieMapper extends BasicEntityDtoMapper<MovieDto, Movie> {

    @Inject
    private PersonRepository personRepository;
    @Inject
    private CoordinatesRepository coordinatesRepository;

    @Override
    @Mapping(source = "mpaaRating", target = "rating")
    public abstract MovieDto mapToDto(Movie movie);

    @Override
    @Mapping(source = "rating", target = "mpaaRating")
    public abstract Movie mapToEntity(MovieDto movieDto);

    @Override
    @Mapping(source = "mpaaRating", target = "rating")
    public abstract List<MovieDto> mapEntitiesToDtos(List<Movie> movies);

    @Override
    @Mapping(source = "rating", target = "mpaaRating")
    public abstract List<Movie> mapDtosToEntities(List<MovieDto> movieDtos);

    @AfterMapping
    public void afterMapToEntity(@MappingTarget Movie entity, MovieDto dto) {
        personRepository.findById(dto.getDirectorId())
                        .ifPresentOrElse(entity::setDirector, () -> {
                            throw new EntityNotFoundException();
                        });

        coordinatesRepository.findById(dto.getCoordinatesId())
                             .ifPresentOrElse(entity::setCoordinates, () -> {
                                 throw new EntityNotFoundException();
                             });
    }

    @AfterMapping
    public void afterMapToDto(@MappingTarget MovieDto dto, Movie entity) {
        dto.setCoordinatesId(entity.getCoordinates().getId());
        dto.setDirectorId(entity.getDirector().getId());
    }
}
