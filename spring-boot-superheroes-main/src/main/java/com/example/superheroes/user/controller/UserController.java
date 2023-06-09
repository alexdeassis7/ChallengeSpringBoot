package com.example.superheroes.user.controller;

import com.example.superheroes.aspect.timed.Traceable;
import com.example.superheroes.user.dto.UserDto;
import com.example.superheroes.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@RestController
@PreAuthorize("permitAll()")
public class UserController {

    private UserService userService;

    @Traceable
    @GetMapping("/api/v1/users")
    public Iterable<UserDto> getUsers() {
        return userService.findAllUsers();
    }

    @Traceable
    @GetMapping("/api/v1/users/{id}")
    public UserDto getUserById(@PathVariable("id") UUID id) {
        return userService.findUserById(id);
    }
    
    @Traceable
    @DeleteMapping("/api/v1/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") UUID id) {
        userService.removeUserById(id);
    }

    @Traceable
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {
        return userService.createUser(userDto, userDto.getPassword());
    }

    @Traceable
    @PutMapping("/api/v1/users/{id}")
    public void putUser(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UserDto userDto
    ) throws NoSuchAlgorithmException {
        userService.updateUser(id, userDto, userDto.getPassword());
    }
}
