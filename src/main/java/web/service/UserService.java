package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> allUsers();

    public List<Role> allRoles();

    public void deleteUser(Long id) ;

    public User getUserById(Long id) ;

    public Role findByRoleName(String role);
}
