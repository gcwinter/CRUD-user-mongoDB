package br.com.gcwinter.crud.service.model;

import lombok.*;

import static java.util.Objects.isNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String complemento;
    private String cep;



    @Override
    public String toString() {
        if(isNull(rua)){
            return "";
        }
        return "%s - %s, %s - %s".formatted(rua,bairro,cidade,estado);
    }
}
