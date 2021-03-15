package springMvc_hibernate.dao;

import org.springframework.stereotype.Component;
import springMvc_hibernate.model.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRole = entityManager.createQuery(" from Role", Role.class).getResultList();
        return allRole;
    }

}
