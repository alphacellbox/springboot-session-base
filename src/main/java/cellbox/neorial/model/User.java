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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RedisHash
@Data
@Builder
public class User implements Serializable {
    @Id
    UUID userId;

    @Email
    @Indexed
    String email;

    @Email
    @Indexed
    String phone;
    String password;
    boolean enable;
    Roles role;
    @Reference
    Set<Session> sessions = new HashSet<Session>();


}
