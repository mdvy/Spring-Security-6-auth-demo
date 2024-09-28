package ru.medov.security_demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.medov.security_demo.domain.dto.UserDto;
import ru.medov.security_demo.domain.dto.UserRequestDto;
import ru.medov.security_demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {
    private final UserService userService;

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @PostMapping
    public UserDto create(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @DeleteMapping
    public UserDto delete(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }


}
