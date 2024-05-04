package cellbox.neorial.model;


import cellbox.neorial.enumaration.Roles;
import jakarta.persistence.GeneratedValue;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
import java.util.UUID;

@RedisHash
@Data
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue
    UUID userId;
    String email;
    String password;
    boolean enable;
    Roles role;


}
