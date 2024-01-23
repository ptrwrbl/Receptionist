package pollub.cs.ptrwrbl.receptionist.services;

import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;

import java.util.List;

public interface UserReservationService {
    List<ReservationDetailsDTO> getAll(String token);

    void add(ReservationDTO reservation, String token);

    ReservationDetailsDTO getOne(Long id);

    void update(Long id, ReservationDTO reservation, String token);

    void delete(Long id);
}