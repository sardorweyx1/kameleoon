package com.kameleoon.task.service;

import com.kameleoon.task.entity.User;
import com.kameleoon.task.payload.UserDto;
import com.kameleoon.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUSer(UserDto userDto) {
        Optional<User> userByMail = userRepository.findUserByMail(userDto.getMail());
        if (userByMail.isPresent()) throw new RuntimeException("User is already exists");
        String passEnc = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());
        User user = User
                .builder()
                .name(userDto.getName())
                .mail(userDto.getMail())
                .password(passEnc)
                .build();
        userRepository.save(user);
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
