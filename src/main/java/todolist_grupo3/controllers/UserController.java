package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import todolist_grupo3.requests.LoginRequest;
import todolist_grupo3.requests.RegisterRequest;
import todolist_grupo3.services.UserService;

@RestController
@RequestMapping("/todolist/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    /* 
    @PostMapping("/user")
    @CrossOrigin("*")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest createUserRequest) {
        try {
            String username = createUserRequest.getUsername();
            String password = createUserRequest.getPassword();
            String email = createUserRequest.getEmail();
            if (username == null || username.trim().isEmpty() || username.length() > 17) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: username must be between 1 and 17 characters");
            }
            if (password == null || password.trim().isEmpty() || password.length() > 25 || password.length() < 8) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: password must be between 8 and 25 characters");
            }
            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+")) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: password must contain at least one lowercase letter, one uppercase letter, one digit, one special character and it can't be any other kind");
            }
            if (email == null || email.trim().isEmpty() || !email.matches("^.+@.+$")) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: invalid email address");
            }
            /*Pendiente de hacer
            if (userService.getEmail(email) != null) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: email already exists");
            }*//* 
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(username, password, email));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }

    }*/
 

    // will handler the exception later
     @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody  RegisterRequest request){
        //userService.register(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.register(request.getUsername(),
         request.getEmail(), request.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(request.getUsername(), request.getPassword()));
    }
    
}
