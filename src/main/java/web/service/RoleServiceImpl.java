package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    @Override
    @Transactional
    public Role findByRoleName(String role) {
        return roleDao.findByRoleName(role);
    }

    @Override
    @Transactional (readOnly = true)
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
}
