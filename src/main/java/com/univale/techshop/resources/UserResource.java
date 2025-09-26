package com.univale.techshop.resources;

import com.univale.techshop.entities.User;
import com.univale.techshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody User user) {
        try {
            User saved = userService.insert(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao gerar.");
        }

    }


}
