package springMvc_hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springMvc_hibernate.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User", User.class).getResultList();
        return allUsers;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User show(int id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
        User user = (User) query.setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public void ubdate(int id, User ubdateUser) {
        User toUbdate = show(id);
        toUbdate.setName(ubdateUser.getName());
        toUbdate.setLatName(ubdateUser.getLatName());
        toUbdate.setEmail(ubdateUser.getEmail());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(int id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("delete User where id = :id");
        query.setParameter("id", id).executeUpdate();
    }
}
