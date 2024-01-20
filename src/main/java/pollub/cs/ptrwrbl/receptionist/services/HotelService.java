package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<HotelDTO> getAllHotels();
    void addHotel(HotelDTO hotelDTO);
    Optional<HotelDTO> getHotelById(Long id);
    void updateHotel(Long id, HotelDTO hotelDTO);
    void deleteHotel(Long id);
}
