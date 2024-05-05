package cellbox.neorial.repository;


import cellbox.neorial.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
  Optional<User> findFirstByEmailOrPhone(String email, String phone);
}