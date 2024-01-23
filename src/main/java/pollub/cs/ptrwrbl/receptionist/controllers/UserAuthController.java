package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.config.JwtTokenUtil;
import pollub.cs.ptrwrbl.receptionist.dtos.UserDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.UserProfile;
import pollub.cs.ptrwrbl.receptionist.mappers.UserMapper;
import pollub.cs.ptrwrbl.receptionist.models.JwtRequest;
import pollub.cs.ptrwrbl.receptionist.models.JwtResponse;
import pollub.cs.ptrwrbl.receptionist.services.UserServiceImpl;

@RestController
@CrossOrigin
public class UserAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserServiceImpl userDetailsService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getOne(HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserProfile user = userMapper.userToUserProfile(userDetailsService.get(username));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}