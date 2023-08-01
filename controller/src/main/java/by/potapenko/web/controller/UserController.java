package by.potapenko.web.controller;

import by.potapenko.model.dto.UserCreationDto;
import by.potapenko.model.dto.UserDto;
import by.potapenko.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Optional<UserDto>> create(@Valid @RequestBody UserCreationDto user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserCreationDto updateUser) {
        return userService.update(id, updateUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.delete(id);
        LOGGER.info("User with id = " + id + " was deleted!");
        return ResponseEntity.noContent().build();
    }
}
