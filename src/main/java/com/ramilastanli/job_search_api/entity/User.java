package com.ramilastanli.job_search_api.entity;

import com.ramilastanli.job_search_api.security.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails { // UserDetails əlavə edildi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    // --- UserDetails Interfeysinin Metodları ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Rolu Spring Security-nin başa düşəcəyi formata salırıq (məs: ROLE_ADMIN)
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesabın vaxtı keçməyib
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesab bloklanmayıb
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Şifrənin vaxtı keçməyib
    }

    @Override
    public boolean isEnabled() {
        return true; // Hesab aktivdir
    }
}