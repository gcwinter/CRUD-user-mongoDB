package br.com.gcwinter.crud.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {

    @Id
    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private final String sex;
    private final String cep;
    private final String numero;


}
