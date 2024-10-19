package com.example.demo.Controllers;

import com.example.demo.DTOS.UserRecordDto;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var user = new User();
        BeanUtils.copyProperties(userRecordDto, user);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<?> getUser(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "name", required = false) String name) {

        if (id != null) {
            Optional<User> user = userRepository.findById(id);
            return user.map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", "Usuário com id " + id + " não encontrado");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((User) response);
                    });
        }

        if (name != null) {
            Optional<User> user = userRepository.findByName(name);
            return user.map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        Map<String, String> response = new HashMap<>();
                        response.put("error", "Usuário com nome " + name + " não encontrado");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((User) response);
                    });
        }

        List<User> allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        Optional<User> user = userRepository.findByName(name);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (!existingUserOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User existingUser = existingUserOpt.get();
        BeanUtils.copyProperties(userRecordDto, existingUser, "id");
        userRepository.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }
}
