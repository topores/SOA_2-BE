package com.example.soa_2.mapper;

import com.example.soa_2.model.Person;
import dto.PersonDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class PersonMapperImpl extends PersonMapper {

    @Override
    public PersonDto mapToDto(Person personDto) {
        if ( personDto == null ) {
            return null;
        }

        PersonDto personDto1 = new PersonDto();

        if ( personDto.getBirthday() != null ) {
            personDto1.setBirthday( new SimpleDateFormat( "dd.MM.yyyy" ).format( personDto.getBirthday() ) );
        }
        personDto1.setName( personDto.getName() );
        personDto1.setWeight( personDto.getWeight() );
        personDto1.setPassportId( personDto.getPassportId() );

        afterMapToDto( personDto1, personDto );

        return personDto1;
    }

    @Override
    public Person mapToEntity(PersonDto person) {
        if ( person == null ) {
            return null;
        }

        Person person1 = new Person();

        try {
            if ( person.getBirthday() != null ) {
                person1.setBirthday( new SimpleDateFormat( "dd.MM.yyyy" ).parse( person.getBirthday() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        person1.setName( person.getName() );
        person1.setWeight( person.getWeight() );
        person1.setPassportId( person.getPassportId() );

        afterMapToEntity( person1, person );

        return person1;
    }

    @Override
    public List<PersonDto> mapEntitiesToDtos(List<Person> personDtoList) {
        if ( personDtoList == null ) {
            return null;
        }

        List<PersonDto> list = new ArrayList<PersonDto>( personDtoList.size() );
        for ( Person person : personDtoList ) {
            list.add( mapToDto( person ) );
        }

        return list;
    }

    @Override
    public List<Person> mapDtosToEntities(List<PersonDto> people) {
        if ( people == null ) {
            return null;
        }

        List<Person> list = new ArrayList<Person>( people.size() );
        for ( PersonDto personDto : people ) {
            list.add( mapToEntity( personDto ) );
        }

        return list;
    }
}
