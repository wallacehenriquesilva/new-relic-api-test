package br.com.wallace.newrelic.api.resources;

import br.com.wallace.newrelic.api.entities.PersonEntity;
import br.com.wallace.newrelic.api.exceptions.CustomException;
import br.com.wallace.newrelic.api.model.PersonModel;
import br.com.wallace.newrelic.api.services.PersonService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wallace
 * @project New-Relic-API-Teste
 * @date 26/04/2020
 **/
@RestController
@RequestMapping("/api/v1/persons")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonEntity> findAll() {
        return personService.findAll();
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonEntity> findOne(@PathVariable("id") String id) {
        return personService.findOne(id);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonEntity> insert(@Validated @RequestBody PersonModel personModel) {
        return personService.insert(personModel);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonEntity> update(@PathVariable("id") String id, @Validated @RequestBody PersonModel personModel) {
        return personService.update(personModel, id);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return personService.delete(id);
    }

    @GetMapping("/error")
    public Flux<PersonEntity> getError() {
        throw new CustomException();
    }
}
