package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;
import pollub.cs.ptrwrbl.receptionist.mappers.HotelMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.HotelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::hotelToHotelDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void addHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.hotelDTOToHotel(hotelDTO);
        hotelRepository.save(hotel);
    }
    @Override
    public Optional<HotelDTO> getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::hotelToHotelDTO);
    }
    @Override
    public void updateHotel(Long id, HotelDTO hotelDTO) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            Hotel _hotel = hotel.get();
            _hotel.setName(hotelDTO.getName());
            _hotel.setLocation(hotelDTO.getLocation());
            _hotel.setDescription(hotelDTO.getDescription());
            _hotel.setImage(hotelDTO.getImage());
            hotelRepository.save(_hotel);
        }
    }
    @Override
    public void deleteHotel(Long id) { hotelRepository.deleteById(id); }
}