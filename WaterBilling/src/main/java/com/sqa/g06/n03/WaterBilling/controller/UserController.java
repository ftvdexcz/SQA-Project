package com.sqa.g06.n03.WaterBilling.controller;

import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.repository.RoleRepository;
import com.sqa.g06.n03.WaterBilling.repository.UserRepository;
import com.sqa.g06.n03.WaterBilling.util.model.ResponseObject;
import com.sqa.g06.n03.WaterBilling.error.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/users")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User user){
        User u = roleRepository.findById(user.getRole().getId()).map(role -> {
            user.setRole(role);
            return userRepository.save(user);
        }).orElseThrow(() -> new AppError("Role_id not found!", 400));

        return ResponseEntity.status(201).body(new ResponseObject(
                "Ok!", "Created!", u
        ));
    }

    @GetMapping("users/{id}")
    ResponseEntity<ResponseObject> findUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.status(200).body(
                    new ResponseObject("Ok!", "Found product!", user)
            );
        }
        return ResponseEntity.status(404).body(
                new ResponseObject("Failed", "Not found!", "")
        );
    }
}
