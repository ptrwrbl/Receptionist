package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;
import pollub.cs.ptrwrbl.receptionist.exceptions.RoomNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.ReservationServiceImpl;
import pollub.cs.ptrwrbl.receptionist.services.RoomServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/rooms"})
@RestController
@CrossOrigin
public class RoomController {
    private final RoomServiceImpl roomService;
    private final ReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAll() {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<RoomDTO>> getHotels(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getHotels(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDTO> add(@Valid @RequestBody RoomDTO room) {
        roomService.add(room);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDetailsDTO>> getReservations(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getRooms(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable Long id, @Valid @RequestBody RoomDTO updatedRoom) {
        if (id < 0) throw new RoomNotFoundException(id);

        roomService.update(id, updatedRoom);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id < 0) throw new RoomNotFoundException(id);

        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}