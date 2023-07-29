package by.potapenko.web.controllers;

import by.potapenko.model.dto.UserCreationDto;
import by.potapenko.model.dto.UserDto;
import by.potapenko.service.UserService;
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


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    static Logger logger = LogManager.getLogger(UserRestController.class);

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
    public ResponseEntity <UserDto> create(@RequestBody UserCreationDto user) {
        return ResponseEntity.ok(userService.create(user));
    }


    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto newUser) {
        return userService.update(id, newUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.deleteById(id);
        logger.info("User was deleted!");
        return ResponseEntity.noContent().build();
    }

}
