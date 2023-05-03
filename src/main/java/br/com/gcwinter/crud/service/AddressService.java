package br.com.gcwinter.crud.service;

import br.com.gcwinter.crud.service.model.Address;

public interface AddressService {
        Address findBy(String zipCode);
}
