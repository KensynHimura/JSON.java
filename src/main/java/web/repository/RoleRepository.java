package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    Role findByRole(String role); // получение роли по имени

}
