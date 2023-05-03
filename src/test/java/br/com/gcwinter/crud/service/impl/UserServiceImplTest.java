package br.com.gcwinter.crud.service.impl;

import br.com.gcwinter.crud.repository.UserRepository;
import br.com.gcwinter.crud.repository.entity.UserEntity;
import br.com.gcwinter.crud.service.AddressService;
import br.com.gcwinter.crud.service.model.Address;
import br.com.gcwinter.crud.service.model.Sex;
import br.com.gcwinter.crud.service.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressService addressService;

    @Test
    @DisplayName("Create user in MongoDB: Given a user is created and save method returns a user entity, when the create method is called, then the save method is invoked")
    void createUserInMongoDB() {

        User user = new User("123", "gabriel winter", "gcwinter96", "11-95255-5555", Sex.MASCULINO, Address.builder().numero("150").cep("0000000").build());
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity("123", "gabriel winter", "gcwinter96", "11-95255-5555", "MASCULINO", "0000000", "150"));

        userServiceImpl.create(user);

        verify(userRepository, atLeastOnce()).save(any(UserEntity.class));

    }

    @Test
    @DisplayName("Delete user in MongoDB: Given a user is deleted, when the delete method is called, then the deleteById method is invoked")
    void deleteUserInMongoDB() {

        userServiceImpl.delete("10");

        verify(userRepository, atLeastOnce()).deleteById(anyString());
    }

    @Test
    @DisplayName("Return list of users: Given the findAll method is called and returns a list with userEntity, when the list method is called, then the findAll method is invoked and all values are validated")
    void returnListOfUsers() {

        when(userRepository.findAll()).thenReturn(List.of(new UserEntity("123", "gabriel winter", "gcwinter96", "11-95255-5555", "MASCULINO", "000000", "150")));
        when(addressService.findBy(anyString())).thenReturn(Address.builder().build());

        List<User> userMongoDb = userServiceImpl.list();

        verify(userRepository, atLeastOnce()).findAll();
        assertAll("create User In MongoDB",
                () -> assertEquals("123", userMongoDb.get(0).getId()),
                () -> assertEquals("gabriel winter", userMongoDb.get(0).getName()),
                () -> assertEquals("gcwinter96", userMongoDb.get(0).getEmail()),
                () -> assertEquals("11-95255-5555", userMongoDb.get(0).getPhone()),
                () -> assertEquals(Sex.MASCULINO, userMongoDb.get(0).getSex())
        );

    }

    @Test
    @DisplayName("Return one user by ID: Given the findById method is called and returns a user entity, when the find method is called, then the findById method is invoked and the user is validated")
    void returnOneUserByID() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(new UserEntity("123", "gabriel winter", "gcwinter96", "11-95255-5555", "MASCULINO", "000", "150")));
        when(addressService.findBy(anyString())).thenReturn(Address.builder().build());

        User userMongoDb = userServiceImpl.find("10");

        verify(userRepository, atLeastOnce()).findById(anyString());
        assertAll("create User In MongoDB",
                () -> assertEquals("123", userMongoDb.getId()),
                () -> assertEquals("gabriel winter", userMongoDb.getName()),
                () -> assertEquals("gcwinter96", userMongoDb.getEmail()),
                () -> assertEquals("11-95255-5555", userMongoDb.getPhone()),
                () -> assertEquals(Sex.MASCULINO, userMongoDb.getSex())
        );

    }

    @Test
    @DisplayName("Edit user: Given a user is created and findById method returns a user entity, when the edit method is called with id and user, then the findById method and save method are invoked")
    void editUser() {
        User user = new User("123", "gabriel winter", "gcwinter96", "11-95255-5555", Sex.MASCULINO, Address.builder().numero("150").cep("0000000").build());
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new UserEntity("123", "gabriel winter", "gcwinter96", "11-95255-5555", "MASCULINO", "000000", "150")));
        when(addressService.findBy(anyString())).thenReturn(Address.builder().build());

        userServiceImpl.edit("123", user);

        verify(userRepository, atLeastOnce()).findById(anyString());
        verify(userRepository, atLeastOnce()).save(any(UserEntity.class));

    }

}