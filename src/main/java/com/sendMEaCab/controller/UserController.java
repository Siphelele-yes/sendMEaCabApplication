package com.sendMEaCab.controller;

import com.sendMEaCab.jwt.JwtTokenProvider;
import com.sendMEaCab.model.Trip;
import com.sendMEaCab.model.User;
import com.sendMEaCab.service.TripService;
import com.sendMEaCab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;

@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else{
            return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
        }
    }

    @GetMapping("api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null ){
          return ResponseEntity.ok(principal);
        }
        else{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    (UsernamePasswordAuthenticationToken) principal;
            User user = userService.findByUsername(usernamePasswordAuthenticationToken.getName());
            Authentication authentication = usernamePasswordAuthenticationToken;
            user.setToken(jwtTokenProvider.generateToken(usernamePasswordAuthenticationToken));

            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("api/user/requestTrip")
    public ResponseEntity<?> requestTrip(@RequestBody Trip trip){
        trip.setTripDate(LocalDate.now());
        return new ResponseEntity<>(tripService.saveTrip(trip),HttpStatus.CREATED);
    }

}
