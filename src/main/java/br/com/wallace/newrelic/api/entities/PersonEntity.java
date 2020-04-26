package br.com.wallace.newrelic.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author wallace
 * @project New-Relic-API-Teste
 * @date 26/04/2020
 **/

@Data
@Builder
@Document("person")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
    @Id
    private String id;

    private String name;
}
