package cellbox.neorial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash
@Builder
public class Session {
  @Id
  private String id;

  private String name;
}