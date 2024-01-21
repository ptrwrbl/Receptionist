package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> getRoomsReservations(Long roomId);
    List<ReservationDTO> getHotelsReservations(Long hotelId);
    List<ReservationDTO> getUsersReservations(Long userId);
    void addReservation(ReservationDTO reservation);
    ReservationDTO getReservationById(Long id);
    void updateReservation(Long id, ReservationDTO reservation);
    void deleteReservation(Long id);
}