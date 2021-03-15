package springMvc_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springMvc_hibernate.dao.RoleDao;
import springMvc_hibernate.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

}
