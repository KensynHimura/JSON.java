package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.model.Role;
import web.model.User;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }


    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User findByUserFirstName(String email) {
//        return userRepository.findByFirstName (userFirstName);
        return userRepository.findByEmail(email);

    }

    public Role findByRoleName(String role) {
        return roleRepository.findByRole(role);
    }


}
