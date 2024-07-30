package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import todolist_grupo3.exception.HttpException;
import todolist_grupo3.requests.LoginRequest;
import todolist_grupo3.requests.RegisterRequest;
import todolist_grupo3.services.UserService;

@RestController
@RequestMapping("/todolist/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

     @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  RegisterRequest request){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request.getUsername(),
            request.getEmail(), request.getPassword()));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(request.getUsername(), 
            request.getPassword()));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
