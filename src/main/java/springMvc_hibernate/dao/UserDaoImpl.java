package springMvc_hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springMvc_hibernate.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery("select User from User", User.class).getResultList();
        return allUsers;
    }
//
//    @Override
//    public void save(User user) {
//        Session session = entityManager.unwrap(Session.class);
//        session.save(user);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public User show(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        TypedQuery<User> query = session.createQuery("from User where id = :id");
//        User user = (User) query.setParameter("id", id).getSingleResult();
//        return user;
//    }
//
//    @Override
//    public void ubdate(int id, User ubdateUser) {
//        User toUbdate = show(id);
//        toUbdate.setName(ubdateUser.getName());
//        toUbdate.setLatName(ubdateUser.getLatName());
//        toUbdate.setEmail(ubdateUser.getEmail());
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public void delete(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        TypedQuery<User> query = session.createQuery("delete User where id = :id");
//        query.setParameter("id", id).executeUpdate();
//    }
}