package com.example.soa_2.mapper;

import com.example.soa_2.model.Location;
import dto.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "CDI")
public abstract class LocationMapper extends BasicEntityDtoMapper<LocationDto, Location> {
}
