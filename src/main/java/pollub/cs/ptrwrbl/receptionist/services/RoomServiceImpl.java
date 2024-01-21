package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;
import pollub.cs.ptrwrbl.receptionist.entities.Room;
import pollub.cs.ptrwrbl.receptionist.exceptions.HotelNotFoundException;
import pollub.cs.ptrwrbl.receptionist.exceptions.RoomNotFoundException;
import pollub.cs.ptrwrbl.receptionist.mappers.RoomMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.HotelRepository;
import pollub.cs.ptrwrbl.receptionist.repositories.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<RoomDTO> getAll() {
        return roomRepository.findAll().stream()
                .map(roomMapper::roomToRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getHotels(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId).stream()
                .map(roomMapper::roomToRoomDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void add(RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException(roomDTO.getHotelId()));

        Room room = roomMapper.roomDTOToRoom(roomDTO);
        room.setHotel(hotel);
        roomRepository.save(room);
    }

    @Override
    public RoomDTO getOne(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::roomToRoomDTO)
                .orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public void update(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));

        room.setName(roomDTO.getName());
        room.setDescription(roomDTO.getDescription());
        room.setPrice(roomDTO.getPrice());
        room.setImage(roomDTO.getImage());

        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException(roomDTO.getHotelId()));
        room.setHotel(hotel);

        roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
        roomRepository.delete(room);
    }
}