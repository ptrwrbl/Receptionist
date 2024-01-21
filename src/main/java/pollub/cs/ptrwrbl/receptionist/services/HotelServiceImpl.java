package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;
import pollub.cs.ptrwrbl.receptionist.exceptions.HotelNotFoundException;
import pollub.cs.ptrwrbl.receptionist.mappers.HotelMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelDTO> getAll() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::hotelToHotelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void add(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.hotelDTOToHotel(hotelDTO);
        hotelRepository.save(hotel);
    }

    @Override
    public HotelDTO getOne(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::hotelToHotelDTO)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public void update(Long id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));

        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setImage(hotelDTO.getImage());
        hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
        hotelRepository.delete(hotel);
    }
}