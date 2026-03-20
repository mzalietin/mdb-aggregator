package me.mzalietin.mdbproject.user.infrastructure.rest;

import me.mzalietin.mdbproject.user.application.UserUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    UserUseCases userUseCases;

    @DeleteMapping("/{user}")
    public void deleteUser(@PathVariable("user") String username) {
        userUseCases.deleteUser(username);
    }
}
