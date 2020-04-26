package br.com.wallace.newrelic.api.services;

import br.com.wallace.newrelic.api.entities.PersonEntity;
import br.com.wallace.newrelic.api.model.PersonModel;
import br.com.wallace.newrelic.api.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author wallace
 * @project New-Relic-API-Teste
 * @date 26/04/2020
 **/
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Flux<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    public Mono<PersonEntity> findOne(final String id) {
        return personRepository.findById(id);
    }

    public Mono<PersonEntity> insert(final PersonModel personModel) {
        return personRepository.save(PersonEntity.builder().name(personModel.getName()).build());
    }

    public Mono<PersonEntity> update(final PersonModel personModel, final String id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .map(person -> {
                    person.setName(personModel.getName());
                    return person;
                })
                .flatMap(person -> personRepository.save(person).then(Mono.just(person)));
    }

    public Mono<Void> delete(final String id) {
        return personRepository.deleteById(id);
    }
}
