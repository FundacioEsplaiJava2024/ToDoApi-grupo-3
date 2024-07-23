package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import todolist_grupo3.requests.CreateUserRequest;
import todolist_grupo3.services.UserService;

@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @CrossOrigin("*")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();
        String email = createUserRequest.getEmail();
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(username, password, email));
    }
}
