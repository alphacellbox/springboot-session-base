package cellbox.neorial.model;


import cellbox.neorial.enumaration.Roles;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RedisHash
@Data
@Builder
public class User implements Serializable, UserDetails {
    @Id
    UUID userId;

    @Email
    @Indexed
    String email;

    String password;
    boolean enable;
    Roles role;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}
