package pollub.cs.ptrwrbl.receptionist.repositories;

import org.springframework.data.repository.CrudRepository;
import pollub.cs.ptrwrbl.receptionist.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
