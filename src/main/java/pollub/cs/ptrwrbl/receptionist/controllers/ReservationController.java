package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.config.JwtTokenUtil;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.entities.User;
import pollub.cs.ptrwrbl.receptionist.exceptions.ReservationNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.ReservationServiceImpl;
import pollub.cs.ptrwrbl.receptionist.services.UserServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/reservations"})
@RestController
public class ReservationController {
    private final ReservationServiceImpl reservationService;
    private final UserServiceImpl userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAll() {
        return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/mine")
    public ResponseEntity<List<ReservationDTO>> getMyReservations(HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(reservationService.getUsers(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<ReservationDTO>> getHotels(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getHotels(id), HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<List<ReservationDTO>> getRooms(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getRooms(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationDTO>> getUsers(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getUsers(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDTO> add(@Valid @RequestBody ReservationDTO reservation) {
        reservationService.add(reservation);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @Valid @RequestBody ReservationDTO updatedReservation) {
        if (id < 0) throw new ReservationNotFoundException(id);

        reservationService.update(id, updatedReservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id < 0) throw new ReservationNotFoundException(id);

        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}