package com.example.soa_2.mapper;

import com.example.soa_2.model.Coordinates;
import dto.CoordinatesDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-13T02:55:58+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Azul Systems, Inc.)"
)
@ApplicationScoped
public class CoordinatesMapperImpl extends CoordinatesMapper {

    @Override
    public CoordinatesDto mapToDto(Coordinates entity) {
        if ( entity == null ) {
            return null;
        }

        CoordinatesDto coordinatesDto = new CoordinatesDto();

        coordinatesDto.setX( entity.getX() );
        coordinatesDto.setY( entity.getY() );

        return coordinatesDto;
    }

    @Override
    public Coordinates mapToEntity(CoordinatesDto dto) {
        if ( dto == null ) {
            return null;
        }

        Coordinates coordinates = new Coordinates();

        coordinates.setX( dto.getX() );
        coordinates.setY( dto.getY() );

        return coordinates;
    }

    @Override
    public List<CoordinatesDto> mapEntitiesToDtos(List<Coordinates> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CoordinatesDto> list = new ArrayList<CoordinatesDto>( entities.size() );
        for ( Coordinates coordinates : entities ) {
            list.add( mapToDto( coordinates ) );
        }

        return list;
    }

    @Override
    public List<Coordinates> mapDtosToEntities(List<CoordinatesDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Coordinates> list = new ArrayList<Coordinates>( dtos.size() );
        for ( CoordinatesDto coordinatesDto : dtos ) {
            list.add( mapToEntity( coordinatesDto ) );
        }

        return list;
    }
}
