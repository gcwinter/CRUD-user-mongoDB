package br.com.gcwinter.crud.service;

import br.com.gcwinter.crud.service.model.User;

import java.util.List;

public interface UserService {

    void create(User user);
    void delete(String id);

    List<User> list();

    User find(String id);

    void edit(String id, User user);
}
