package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name; // уникальное значение


    private String password;

   @ManyToMany (cascade = CascadeType.ALL)
  @JoinTable (joinColumns = @JoinColumn (name = "user_id"),
              inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set <Role> roles;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    }

    public User() {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     //   return roles.stream().map(r->new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = new HashSet<>();}

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
