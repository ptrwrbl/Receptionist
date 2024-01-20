package pollub.cs.ptrwrbl.receptionist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.exceptions.HotelNotFoundException;
import pollub.cs.ptrwrbl.receptionist.services.HotelServiceImpl;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping({ "/hotels" })
@RestController
public class HotelController {
    private final HotelServiceImpl hotelService;
    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HotelDTO> addNewHotel(@RequestBody HotelDTO hotel, BindingResult bindingResult) {
        hotelService.addHotel(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        Optional<HotelDTO> hotel = hotelService.getHotelById(id);
        if (hotel.isEmpty()) {
            throw new HotelNotFoundException(id);
        }

        return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO updatedHotel, BindingResult bindingResult) {
        if ((id < 0) || (hotelService.getHotelById(id).isEmpty())) {
            throw new HotelNotFoundException(id);
        }

        hotelService.updateHotel(id, updatedHotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        if ((id < 0) || (hotelService.getHotelById(id).isEmpty())) {
            throw new HotelNotFoundException(id);
        }

        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}