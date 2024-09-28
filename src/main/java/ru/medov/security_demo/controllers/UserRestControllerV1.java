package ru.medov.security_demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.medov.security_demo.domain.dto.UserDto;
import ru.medov.security_demo.domain.dto.UserRequestDto;
import ru.medov.security_demo.services.UserService;
import ru.medov.security_demo.util.RestErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {
    private final UserService userService;

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping
    public UserDto create(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        if (userService.exists(id)) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestErrorResponse> exceptionHandler(UsernameNotFoundException e){
            return new ResponseEntity<>(new RestErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
