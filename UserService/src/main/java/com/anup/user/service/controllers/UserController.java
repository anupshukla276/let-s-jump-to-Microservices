package com.anup.user.service.controllers;

import com.anup.user.service.entities.User;
import com.anup.user.service.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    //ctreate
    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user) {
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
        //get single user
//        @GetMapping("/{userId}")
//        public User getUser(@PathVariable String userId) {
//            // Call service method and get ResponseEntity
//            User response = userServices.getUser(userId);
//            // Return the ResponseEntity directly
//            return response;
//        }
        @GetMapping("/{userId}")
        public ResponseEntity<User> getUser(@PathVariable String userId) {
            // Call service method and get ResponseEntity
            ResponseEntity<User> responseEntity = userServices.getUser(userId);

            // Return the ResponseEntity directly
            return responseEntity;
        }

        //all user get
        @GetMapping
        public ResponseEntity<List<User>> getAllUser() {
            // Call service method and get ResponseEntity
            ResponseEntity<List<User>> response = userServices.getAllUser();
            // Return the ResponseEntity directly
            return response;
        }
}
