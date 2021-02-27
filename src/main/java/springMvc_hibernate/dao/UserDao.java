package springMvc_hibernate.dao;

import springMvc_hibernate.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    User show(int id);

    void ubdate(int id, User user);

    void delete(int id);
}
