package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDetailsDTO> getAll();

    List<ReservationDetailsDTO> getRooms(Long roomId);

    List<ReservationDetailsDTO> getHotels(Long hotelId);

    List<ReservationDetailsDTO> getUsers(Long userId);

    void add(ReservationDTO reservation);

    ReservationDetailsDTO getOne(Long id);

    void update(Long id, ReservationDTO reservation);

    void delete(Long id);
}