package com.example.soa_2.service;

import com.example.soa_2.mapper.PersonMapper;
import com.example.soa_2.model.Person;
import com.example.soa_2.repository.PersonRepository;
import dto.PersonDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PersonService extends AbstractCrudService<PersonDto, Person> {

    @Inject
    public PersonService(PersonRepository repository,
                         PersonMapper mapper) {
        super(repository, mapper);
    }

    public PersonService() {
    }

    public List<PersonDto> findAllByDirectorLessThen(String director) {
        return mapper.mapEntitiesToDtos(((PersonRepository) repository).findAllByDirectorLessThan(director));
    }
}
