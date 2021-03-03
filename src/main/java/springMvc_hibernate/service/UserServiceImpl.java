package springMvc_hibernate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springMvc_hibernate.dao.UserDao;
import springMvc_hibernate.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional()
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional()
    public User show(int id) {
        return userDao.show(id);
    }

    @Override
    @Transactional()
    public void ubdate(int id, User user) {
        userDao.ubdate(id,user);
    }

    @Override
    @Transactional()
    public void delete(int id) {
        userDao.delete(id);
    }
}
