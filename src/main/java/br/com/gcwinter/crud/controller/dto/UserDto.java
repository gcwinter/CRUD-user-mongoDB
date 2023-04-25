package br.com.gcwinter.crud.controller.dto;

import lombok.Data;

@Data
public class UserDto {

    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private final String sex;

}
