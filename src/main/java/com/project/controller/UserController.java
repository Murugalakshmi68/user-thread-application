package com.project.controller;

import com.project.entity.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/save")
    public ResponseEntity<String> createUser(@RequestBody User user){
        service.addUser(user);
        return ResponseEntity.ok("User Created Successfully");
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User u = service.getUser(id);
        return ResponseEntity.ok(u);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        List<User> u = service.getAllUser();
        return ResponseEntity.ok(u);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id); // ensure path ID is used
        service.updateUser(user);
        return ResponseEntity.ok("User Updated Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }

}
