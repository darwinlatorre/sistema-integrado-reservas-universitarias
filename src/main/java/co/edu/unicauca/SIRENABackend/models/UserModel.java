package co.edu.unicauca.SIRENABackend.models;

<<<<<<< HEAD
import jakarta.persistence.*;
=======
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
>>>>>>> c89f3b2a845cd617136907c49fb0e553b4823d07
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", unique = true)
    private Integer ID;

    @Column(name = "usr_name", nullable = false, length = 20)
    private String name;

    @Column(name = "usr_firstname", nullable = false, length = 20)
    private String firstName;

    @Column(name = "usr_lastname", nullable = false, length = 20)
    private String lastName;

    @Column(name = "usr_email", nullable = false, length = 70, unique = true)
    private String email;

    @Column(name = "usr_password", nullable = false, length = 45)
    private String password;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "role_id")
=======
    @JoinColumn(name = "usr_role")
>>>>>>> c89f3b2a845cd617136907c49fb0e553b4823d07
    private RoleModel userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.getRoleName()));
    }

    @Override
    public String getUsername() {
        return email;
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
}
