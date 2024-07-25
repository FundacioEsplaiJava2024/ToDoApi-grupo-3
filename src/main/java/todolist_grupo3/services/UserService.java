package todolist_grupo3.services;

import todolist_grupo3.entities.User;

public interface  UserService {
    //User createUser(String username, String password, String email);
    User register(String username, String email, String password);
    String login(String username, String password);
}