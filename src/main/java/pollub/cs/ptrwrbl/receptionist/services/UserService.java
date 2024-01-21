package pollub.cs.ptrwrbl.receptionist.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pollub.cs.ptrwrbl.receptionist.dtos.UserDTO;
import pollub.cs.ptrwrbl.receptionist.entities.User;

public interface UserService extends UserDetailsService {
    User save(UserDTO user);
}
