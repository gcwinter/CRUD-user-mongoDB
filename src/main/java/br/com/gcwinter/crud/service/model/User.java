package br.com.gcwinter.crud.service.model;

import lombok.Data;

@Data
public class User {


    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private final Sex sex;
    private final Address address;


}
