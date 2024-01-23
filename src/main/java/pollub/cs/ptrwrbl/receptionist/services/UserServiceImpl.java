package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.dtos.UserDTO;
import pollub.cs.ptrwrbl.receptionist.entities.User;
import pollub.cs.ptrwrbl.receptionist.exceptions.UserNotFoundException;
import pollub.cs.ptrwrbl.receptionist.mappers.UserMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.UserRepository;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User get(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = get(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), new ArrayList<>());
    }

    public User save(UserDTO user) {
        User newUser = userMapper.userDTOToUser(user);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepo.save(newUser);
    }
}