package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role findByRoleName(String role) {
        return entityManager.createQuery("select role from Role role where role.role=:role", Role.class)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find (Role.class, id);
    }
}
