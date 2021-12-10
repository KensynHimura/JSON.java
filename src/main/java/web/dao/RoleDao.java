package web.dao;

import web.model.Role;


import java.util.List;

public interface RoleDao {

    List<Role> allRoles(); // получение всех ролей

    Role findByRoleName(String role); // получение роли по имени

}
