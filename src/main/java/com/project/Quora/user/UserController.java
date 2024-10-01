package com.project.Quora.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/quora/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.saveUserToDatabase(newUser));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(required = true) String userId) {
        Optional<User> userData = this.userService.getUserById(HexToUUIdConvertor.hexToUUID(userId));
        if(userData.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserDto(userData.get().getEmail() , userData.get().getUsername()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("data not found");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(required = true) String userId , @RequestBody(required = true) User userDetails) {
        User userData = this.userService.updateUser(HexToUUIdConvertor.hexToUUID(userId) , userDetails);
        if(userData != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new UserDto(userData.getEmail() , userData.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("data not found");
    }
}


