package ru.medov.security_demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medov.security_demo.domain.User;
import ru.medov.security_demo.domain.dto.UserDto;
import ru.medov.security_demo.domain.dto.UserRequestDto;
import ru.medov.security_demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserRepository repository;

    public UserService(PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;


        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public UserDto findById(Long id) {

        User user = repository.findById(id)

                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> findAll() {

        List<User> users = repository.findAll();

        return users.stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList());

    }

    public UserDto create(UserRequestDto userRequestDto) {

        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User user = repository.save(modelMapper.map(userRequestDto, User.class));

        return modelMapper.map(user, UserDto.class);
    }
}
