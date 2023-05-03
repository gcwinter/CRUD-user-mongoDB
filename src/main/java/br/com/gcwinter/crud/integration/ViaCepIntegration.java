package br.com.gcwinter.crud.integration;

import br.com.gcwinter.crud.integration.resource.CepResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ViaCepIntegration {

    private final String url = "https://viacep.com.br/ws/{cep}/{formato}/";
    private final RestTemplate restTemplate;
    private static final String FORMAT = "json";

    public CepResource findCep(String cep) {
        if(isNull(cep)){
            return new CepResource();
        }

        String url = this.url.replace("{cep}", cep).replace("{formato}", FORMAT);
        return restTemplate.getForObject(url, CepResource.class);
    }




}
