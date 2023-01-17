package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.auth.Auth;
import dev.florinchristian.airportbackend.exception.UserNotFoundException;
import dev.florinchristian.airportbackend.exception.WrongCredentialsException;
import dev.florinchristian.airportbackend.model.Credentials;
import dev.florinchristian.airportbackend.model.Role;
import dev.florinchristian.airportbackend.model.User;
import dev.florinchristian.airportbackend.model.UserRole;
import dev.florinchristian.airportbackend.repository.RoleRepository;
import dev.florinchristian.airportbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> checkCredentials(@RequestParam(name = "email") @NonNull String email, @RequestParam @NonNull String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null)
            throw new UserNotFoundException();

        if (!user.getHashedPassword().equals(Auth.getMD5Hash(password)))
            throw new WrongCredentialsException();

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User registerUser(@RequestBody @NonNull Credentials credentials) throws NoSuchAlgorithmException {
        Role userRole = roleRepository.findById(2).orElse(new Role(2, UserRole.ROLE_USER));

        User user = new User();

        user.setEmail(credentials.getEmail());
        user.setHashedPassword(Auth.getMD5Hash(credentials.getPassword()));
        user.setRoles(new HashSet<>() {{
            add(userRole);
        }});

        userRepository.saveAndFlush(user);

        return user;
    }
}
