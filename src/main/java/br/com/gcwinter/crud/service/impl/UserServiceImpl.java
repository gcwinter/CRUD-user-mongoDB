package br.com.gcwinter.crud.service.impl;

import br.com.gcwinter.crud.repository.UserRepository;
import br.com.gcwinter.crud.repository.entity.UserEntity;
import br.com.gcwinter.crud.service.UserService;
import br.com.gcwinter.crud.service.model.Sex;
import br.com.gcwinter.crud.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex().name()
        );

        userRepository.save(userEntity);

    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll().stream().map(user -> new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                Sex.fromString(user.getSex())
        )).toList();

    }

    @Override
    public User find(String id) {

        return userRepository.findById(id).map(user -> new User(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                Sex.fromString(user.getSex())
        )).get();
    }

    @Override
    public void edit(String id, User user) {

        User userEntity = find(id);
        User userEdited = new User(
                userEntity.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex());
        create(userEdited);

    }
}
