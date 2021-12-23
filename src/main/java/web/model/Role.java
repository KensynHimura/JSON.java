package web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        String admin = "ROLE_ADMIN";
        if (role.equals(admin)){
            return "ADMIN";
        } else
        return "USER";
    }
}
