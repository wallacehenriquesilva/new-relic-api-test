package br.com.wallace.newrelic.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wallace
 * @project New-Relic-API-Teste
 * @date 26/04/2020
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
    private String name;
}
