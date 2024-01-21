package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

@Mapper
public interface ReservationMapper {
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "userId", source = "user.id")
    ReservationDTO reservationToReservationDTO(Reservation room);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reservation reservationDTOToReservation(ReservationDTO dto);
}
