package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> allRoles(); // получение всех ролей

    Role findByRoleName(String role); // получение роли по имени

}
