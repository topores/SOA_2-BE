package com.example.soa_2.mapper;

import com.example.soa_2.model.Coordinates;
import dto.CoordinatesDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "CDI")
public abstract class CoordinatesMapper extends BasicEntityDtoMapper<CoordinatesDto, Coordinates> {
}
