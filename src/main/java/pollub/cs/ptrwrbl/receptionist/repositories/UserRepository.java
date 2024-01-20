package pollub.cs.ptrwrbl.receptionist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pollub.cs.ptrwrbl.receptionist.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
