package todolist_grupo3.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.Role;
import todolist_grupo3.entities.User;
import todolist_grupo3.repo.UserRepository;
import todolist_grupo3.security.JwtService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User register(String username, String email, String password) {
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .registerDate(LocalDate.now())
                .build();
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

            var jwtToken = jwtService.generateToken((User) auth.getPrincipal());
        return jwtToken;
    }
        
        /*
    @Override
    public User createUser(String username, String password, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        return userRepository.save(newUser);
    }*/
}
