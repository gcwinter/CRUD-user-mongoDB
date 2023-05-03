package br.com.gcwinter.crud.controller;

import br.com.gcwinter.crud.controller.dto.UserDto;
import br.com.gcwinter.crud.service.UserService;
import br.com.gcwinter.crud.service.model.Address;
import br.com.gcwinter.crud.service.model.Sex;
import br.com.gcwinter.crud.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public void createUser(@RequestBody UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                Sex.fromString(userDto.getSex()),
                Address.builder().cep(userDto.getCep()).numero(userDto.getNumero()).build()
        );
        userService.create(user);
    }

    @GetMapping("api/user")
    public List<UserDto> listUsers() {
        return userService.list().stream().map(user -> new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex().name(),
                user.getAddress().getCep(),
                user.getAddress().toString(),
                user.getAddress().getNumero()
        )).toList();
    }

    @GetMapping("api/user/{id}")
    public UserDto findUser(@PathVariable String id) {
        User userModel = userService.find(id);
        return Optional.of(userModel).map(user -> new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getSex().name(),
                user.getAddress().getCep(),
                user.getAddress().toString(),
                user.getAddress().getNumero()
        )).get();
    }

    @DeleteMapping("api/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

    @PutMapping("api/user/{id}")
    public void editUser(@PathVariable String id, @RequestBody UserDto userDto) {


        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                Sex.fromString(userDto.getSex()),
                Address.builder().cep(userDto.getCep()).numero(userDto.getNumero()).build()

        );


        userService.edit(id, user);


    }


}
