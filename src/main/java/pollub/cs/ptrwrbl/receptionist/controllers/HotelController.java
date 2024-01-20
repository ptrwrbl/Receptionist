package pollub.cs.ptrwrbl.receptionist.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.services.HotelServiceImpl;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HotelController {
    private final HotelServiceImpl hotelService;
    @RequestMapping({ "/hotels" })
    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.getAllHotels();
    }
}