package springMvc_hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import springMvc_hibernate.model.Role;
import springMvc_hibernate.model.User;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
}
