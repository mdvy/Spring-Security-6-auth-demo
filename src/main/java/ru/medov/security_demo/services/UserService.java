package ru.medov.security_demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medov.security_demo.domain.User;
import ru.medov.security_demo.domain.dto.UserDto;
import ru.medov.security_demo.domain.dto.CreateUserRequestDto;
import ru.medov.security_demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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

                .orElseThrow(() -> new UsernameNotFoundException("user not found with ID = " + id));
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> findAll() {

        List<User> users = repository.findAll();

        return users.stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList());

    }

    public UserDto create(CreateUserRequestDto createUserRequestDto) {

        createUserRequestDto.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));

        User user = repository.save(modelMapper.map(createUserRequestDto, User.class));

        return modelMapper.map(user, UserDto.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    public UserDto findByUsername(String username){
        User user = repository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return modelMapper.map(user, UserDto.class);
    }

}
