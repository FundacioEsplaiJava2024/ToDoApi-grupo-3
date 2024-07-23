package todolist_grupo3.services;

import java.util.List;

import todolist_grupo3.entities.User;

public interface UserService {
    User createUser(String username, String password, String email);

    List<User> getAllUsers();

    User getUserById(Integer id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);
}