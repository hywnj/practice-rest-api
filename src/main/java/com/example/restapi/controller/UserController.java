package com.example.restapi.controller;

import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("* createUser");
        log.debug(user.toString()); // id 없음

        User savedUser = userRepository.save(user); // id 있음
        log.debug(savedUser.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("* getAllUsers");
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("* getUserById");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) { // 존재하면
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();

        // 다른 방식
        // return userRepository.findById(id)
        //        .map(ResponseEntity::ok) // 존재하면(=not null 이면) | ResponseEntity 안에 ok라는 메서드
        //        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("* updateUser");
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()) {
            user.setId(id);
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        log.info("* deleteUserById");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) { // 존재하면
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
