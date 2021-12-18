package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String userName); // получение юзера по имени пользователя

}
