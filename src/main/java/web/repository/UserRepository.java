package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByFirstName(String userFirstName); // получение юзера по имени пользователя

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = (:email)")
    User findByEmail (String email);
}
