package com.anup.user.service.services;

import com.anup.user.service.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServices {
    //user operartions

    //create
    User saveUser(User user);
    //get all users
    ResponseEntity<List<User>> getAllUser();;

    //get single user
   ResponseEntity<User> getUser(String userId);

    // Update
//    User updateUser(String userId, User updatedUser);
//
//    // Delete
//    void deleteUser(String userId);

}
