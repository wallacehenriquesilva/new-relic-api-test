package br.com.wallace.newrelic.api.services;

import br.com.wallace.newrelic.api.entities.PersonEntity;
import br.com.wallace.newrelic.api.model.PersonModel;
import br.com.wallace.newrelic.api.repositories.PersonRepository;
import com.newrelic.api.agent.NewRelic;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
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
        HashMap<String, Object> personMap = new HashMap<>();
        personMap.put("name", personModel.getName());
        personMap.put("years", personModel.getYears());
        NewRelic.getAgent().getInsights().recordCustomEvent("PersonsEvent", personMap);

        return personRepository.save(PersonEntity.builder().name(personModel.getName()).years(personModel.getYears()).build());
    }

    public Mono<PersonEntity> update(final PersonModel personModel, final String id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .filter(Objects::nonNull)
                .map(person -> {
                    person.setName(personModel.getName());
                    person.setYears(personModel.getYears());
                    return person;
                })
                .flatMap(person -> personRepository.save(person).then(Mono.just(person)));
    }

    public Mono<Void> delete(final String id) {
        return personRepository.deleteById(id);
    }
}
