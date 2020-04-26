package br.com.wallace.newrelic.api.repositories;

import br.com.wallace.newrelic.api.entities.PersonEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author wallace
 * @project New-Relic-API-Teste
 * @date 26/04/2020
 **/
public interface PersonRepository extends ReactiveMongoRepository<PersonEntity, String> {

}
