package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

@Mapper
public interface ReservationMapper {
    @Mapping(target = "roomId", source = "reservation.room.id")
    @Mapping(target = "userId", source = "reservation.user.id")
    ReservationDTO reservationToReservationDTO(Reservation reservation);


    @Mapping(target = "roomId", source = "reservation.room.id")
    @Mapping(target = "userId", source = "reservation.user.id")
    @Mapping(target = "roomName", source = "reservation.room.name")
    @Mapping(target = "hotelName", source = "reservation.room.hotel.name")
    @Mapping(target = "location", source = "reservation.room.hotel.location")
    @Mapping(target = "price", source = "reservation.room.price")
    ReservationDetailsDTO reservationToReservationDetailsDTO(Reservation reservation);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reservation reservationDTOToReservation(ReservationDTO dto);
}
