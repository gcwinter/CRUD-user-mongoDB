package br.com.gcwinter.crud.service.impl;

import br.com.gcwinter.crud.repository.UserRepository;
import br.com.gcwinter.crud.repository.entity.UserEntity;
import br.com.gcwinter.crud.service.AddressService;
import br.com.gcwinter.crud.service.UserService;
import br.com.gcwinter.crud.service.model.Address;
import br.com.gcwinter.crud.service.model.Sex;
import br.com.gcwinter.crud.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    @Override
    public void create(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex().name(),
                user.getAddress().getCep(),
                user.getAddress().getNumero()
        );

        userRepository.save(userEntity);

    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> list() {

        return userRepository.findAll().stream().map(user -> {
            Address address = addressService.findBy(user.getCep());
            address.setNumero(user.getNumero());
            return new User(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    Sex.fromString(user.getSex()),
                    address
            );
        }).toList();

    }

    @Override
    public User find(String id) {

        return userRepository.findById(id).map(user -> {
            Address address = addressService.findBy(user.getCep());
            address.setNumero(user.getNumero());
            return new User(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    Sex.fromString(user.getSex()),
                    address

            );
        }).get();
    }

    @Override
    public void edit(String id, User user) {

        User userEntity = find(id);
        User userEdited = new User(
                userEntity.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex(),
                user.getAddress()
        );
        create(userEdited);

    }


}
