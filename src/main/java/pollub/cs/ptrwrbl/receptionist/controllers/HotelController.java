package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;
import pollub.cs.ptrwrbl.receptionist.exceptions.HotelNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.HotelServiceImpl;
import pollub.cs.ptrwrbl.receptionist.services.ReservationServiceImpl;
import pollub.cs.ptrwrbl.receptionist.services.RoomServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/hotels"})
@RestController
@CrossOrigin
public class HotelController {
    private final HotelServiceImpl hotelService;
    private final RoomServiceImpl roomService;
    private final ReservationServiceImpl reservationService;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAll() {
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HotelDTO> add(@Valid @RequestBody HotelDTO hotel) {
        hotelService.add(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(hotelService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getHotels(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDetailsDTO>> getReservations(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getHotels(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> update(@PathVariable Long id, @Valid @RequestBody HotelDTO updatedHotel) {
        if (id < 0) throw new HotelNotFoundException(id);

        hotelService.update(id, updatedHotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (id < 0) throw new HotelNotFoundException(id);

        hotelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}