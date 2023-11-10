package com.mictlanes.Arvideys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mictlanes.Arvideys.Models.*;
import com.mictlanes.Arvideys.Services.UserServices;

import java.util.List;

import javax.management.AttributeNotFoundException;

@RestController
@RequestMapping(path="/api/users")
public class UserController {

    private final UserServices userServices;

	@Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    // GET all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
    	List<User> users = userServices.getAllUsers();
    	return ResponseEntity.ok(users);
    }

    // GET a single user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws AttributeNotFoundException {
    	User user = userServices.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, 
            @RequestParam Long discountCodesId) {
    	try {
    		User newUser = userServices.createUser(user, discountCodesId);
    		return ResponseEntity.ok(newUser);
    	} catch (AttributeNotFoundException e) {
    		return ResponseEntity.notFound().build();
}
}

    // PUT to update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, 
                                           @RequestBody User UserDetails, 
                                           @RequestParam Long discountCodesId) {
        try {
            User updatedUser = userServices.updateUser(id, UserDetails, discountCodesId);
            return ResponseEntity.ok(updatedUser);
        } catch (AttributeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	userServices.deleteUser(id);
		return ResponseEntity.ok().build();
    }
}