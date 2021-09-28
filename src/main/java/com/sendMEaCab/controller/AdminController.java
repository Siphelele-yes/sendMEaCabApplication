package com.sendMEaCab.controller;

import com.sendMEaCab.model.User;
import com.sendMEaCab.service.TripService;
import com.sendMEaCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @PutMapping("/api/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User exist = userService.findByUsername(user.getUsername());
        if(exist != null && !exist.getUserId().equals(user.getUserId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else
            return new ResponseEntity<>(userService.updateUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/delete-user")
    public ResponseEntity<?> deleteUser(@RequestBody User user){
       userService.deleteUser(user.getUserId());
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/user/find-all-user")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }



}
