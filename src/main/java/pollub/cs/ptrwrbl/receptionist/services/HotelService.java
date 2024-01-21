package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getAll();

    void add(HotelDTO hotelDTO);

    HotelDTO getOne(Long id);

    void update(Long id, HotelDTO hotelDTO);

    void delete(Long id);
}
