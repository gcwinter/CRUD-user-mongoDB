package br.com.gcwinter.crud.service.impl;

import br.com.gcwinter.crud.integration.ViaCepIntegration;
import br.com.gcwinter.crud.integration.resource.CepResource;
import br.com.gcwinter.crud.service.AddressService;
import br.com.gcwinter.crud.service.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    public static final String COUNTRY = "Brasil";
    private final ViaCepIntegration viaCepIntegration;
    @Override
    public Address findBy(String zipCode) {

        CepResource cep = viaCepIntegration.findCep(zipCode);

        return new Address(cep.getLogradouro(),null, cep.getBairro(), cep.getLocalidade(), cep.getUf(), COUNTRY, cep.getComplemento(), cep.getCep());
    }
}
