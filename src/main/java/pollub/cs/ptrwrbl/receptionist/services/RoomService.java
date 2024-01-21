package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getAll();

    List<RoomDTO> getHotels(Long hotelId);

    void add(RoomDTO room);

    RoomDTO getOne(Long id);

    void update(Long id, RoomDTO room);

    void delete(Long id);
}
