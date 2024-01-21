package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAll();
    List<ReservationDTO> getRooms(Long roomId);
    List<ReservationDTO> getHotels(Long hotelId);
    List<ReservationDTO> getUsers(Long userId);
    void add(ReservationDTO reservation);
    ReservationDTO getOne(Long id);
    void update(Long id, ReservationDTO reservation);
    void delete(Long id);
}