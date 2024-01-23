package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;
import pollub.cs.ptrwrbl.receptionist.exceptions.ReservationNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.UserReservationServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/my-reservations"})
@RestController
@CrossOrigin
public class UserReservationController {
    private final UserReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationDetailsDTO>> getAll(HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(reservationService.getAll(token), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationDTO> add(@Valid @RequestBody ReservationDTO reservation, HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        reservationService.add(reservation, token);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDetailsDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getOne(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @Valid @RequestBody ReservationDTO updatedReservation, HttpServletRequest req) {
        String token = req.getHeader("Authorization").substring(7);
        if (id < 0) throw new ReservationNotFoundException(id);

        reservationService.update(id, updatedReservation, token);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id < 0) throw new ReservationNotFoundException(id);

        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}