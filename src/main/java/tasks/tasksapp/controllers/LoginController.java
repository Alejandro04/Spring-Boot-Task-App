package tasks.tasksapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tasks.tasksapp.context.security.JWTAuthtenticationConfig;
import tasks.tasksapp.persistence.entity.User;

@RestController
public class LoginController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("login")
    public User login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass) {

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        return new User(username, encryptedPass, token);
    }
}
