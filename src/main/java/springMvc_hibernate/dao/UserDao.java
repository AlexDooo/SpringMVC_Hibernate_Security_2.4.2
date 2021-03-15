package springMvc_hibernate.dao;

import springMvc_hibernate.model.Role;
import springMvc_hibernate.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    User show(int id);

    Role showRole(int id);

    void update(User ubdateUser, String[] role);

    void delete(int id);

    User getUserByName(String name);

}
