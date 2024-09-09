package tech.ecoelho.generics.controller;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ecoelho.generics.entity.UserEntity;
import tech.ecoelho.generics.entity.dto.UserResponse;
import tech.ecoelho.generics.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll(){
        var response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long userId){
        var response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(UserEntity userEntity){
        UserResponse userResponse = userService.createUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long userId, @RequestBody UserEntity userEntity){
        UserResponse updatedUserResponse = userService.updateUser(userId, userEntity);
        return ResponseEntity.ok(updatedUserResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
