package com.example.soa_2.controller;


import com.example.soa_2.model.Person;
import com.example.soa_2.service.PersonService;
import dto.PersonDto;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/person")
public class PersonController extends AbstractCrudController<PersonDto, Person> {

    @Inject
    public PersonController(PersonService service) {
        super(service);
    }
}
