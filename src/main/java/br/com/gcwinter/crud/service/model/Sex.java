package br.com.gcwinter.crud.service.model;

public enum Sex {
    MASCULINO, FEMININO, OUTROS;

    public static  Sex fromString(String sex) {

        if ("feminino".equalsIgnoreCase(sex)) {
            return FEMININO;
        }
        if ("Masculino".equalsIgnoreCase(sex)) {
            return MASCULINO;
        }

        return OUTROS;
    }
}

