package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{

    private final Map<String, User> adminMap = Collections.singletonMap("ADMIN",
            new User(1L, "ADMIN", "ADMIN", Collections.singleton(new Role(1L, "ROLE_ADMIN"))));

    private final Map<String, User> userMap = Collections.singletonMap("USER",
            new User(2L, "USER", "USER", Collections.singleton(new Role(2L, "ROLE_USER"))));

    @Override
    public User getUserByName(String name) {
        if (adminMap.containsKey(name)) {
            return adminMap.get(name);
        } else if (userMap.containsKey(name)) {
            return userMap.get(name);
        } else
            return null;
    }
    }

