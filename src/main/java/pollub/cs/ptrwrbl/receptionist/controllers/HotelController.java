package pollub.cs.ptrwrbl.receptionist.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.exceptions.HotelNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.HotelServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"/hotels"})
@RestController
public class HotelController {
    private final HotelServiceImpl hotelService;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HotelDTO> addNewHotel(@Valid @RequestBody HotelDTO hotel) {
        hotelService.addHotel(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelDTO updatedHotel) {
        if (id < 0) throw new HotelNotFoundException(id);

        hotelService.updateHotel(id, updatedHotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        if (id < 0) throw new HotelNotFoundException(id);

        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}