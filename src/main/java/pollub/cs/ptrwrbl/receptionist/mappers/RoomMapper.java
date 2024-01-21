package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pollub.cs.ptrwrbl.receptionist.dtos.RoomDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Room;

@Mapper
public interface RoomMapper {
    @Mapping(target = "hotelId", source = "hotel.id")
    RoomDTO roomToRoomDTO(Room room);

    @Mapping(target = "hotel", ignore = true)
    Room roomDTOToRoom(RoomDTO dto);
}
