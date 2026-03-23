package me.mzalietin.mdbproject.user.infrastructure.rest;

import jakarta.validation.Valid;
import me.mzalietin.mdbproject.user.application.UserUseCases;
import me.mzalietin.mdbproject.user.infrastructure.rest.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    UserUseCases userUseCases;

    @GetMapping(path = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable("username") String username) {
        return userUseCases.get(username).map(UserInfo::new).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserInfo user) {
        userUseCases.create(user.toModel());

    }

    @DeleteMapping("/{user}")
    public void deleteUser(@PathVariable("user") String username) {
        userUseCases.delete(username);
    }
}
