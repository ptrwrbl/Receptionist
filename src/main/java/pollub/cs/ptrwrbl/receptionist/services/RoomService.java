package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> getAllRooms();

    List<RoomDTO> getHotelsRooms(Long hotelId);

    void addRoom(RoomDTO room);

    RoomDTO getRoomById(Long id);

    void updateRoom(Long id, RoomDTO room);

    void deleteRoom(Long id);
}
