package springMvc_hibernate.service;

import springMvc_hibernate.model.Role;
import springMvc_hibernate.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User show(int id);

    void ubdate( User user,String[] role);

    void delete(int id);
}
