package springMvc_hibernate.dao;


import org.springframework.stereotype.Component;
import springMvc_hibernate.model.Role;
import springMvc_hibernate.model.User;
import springMvc_hibernate.service.RoleService;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;


@Component
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    private RoleService roleService;

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = entityManager.createQuery(" from User", User.class).getResultList();
        return allUsers;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User show(int id) {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("from User where id = :id");
        User user = (User) query.setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role showRole(int id) {
        TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createQuery("from Role where id = :id");
        Role role = (Role) query.setParameter("id", id).getSingleResult();
        return role;
    }

    @Override
    public void update(User user, String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(showRole(1));
            } else {
                rol.add(showRole(2));
            }
        }
        user.setRoles(rol);
        entityManager.merge(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(int id) {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("delete from User where id = :id");
        query.setParameter("id", id).executeUpdate();
    }

    // сикюрити метод
    @Override
    @SuppressWarnings("unchecked")
    public User getUserByName(String name) {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("from User where userName = :userName");
        query.setParameter("userName", name);
        User user = query.getSingleResult();
        if (!user.getUsername().contains(name)) {
            return null;
        }

        return user;

    }
}