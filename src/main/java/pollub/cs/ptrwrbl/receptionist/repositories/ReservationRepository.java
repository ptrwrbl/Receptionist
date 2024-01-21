package pollub.cs.ptrwrbl.receptionist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserId(Long userId);
    List<Reservation> findAllByRoomId(Long roomId);

    @Query("SELECT r FROM Reservation r JOIN r.room rr WHERE rr.hotel.id = :hotelId")
    List<Reservation> findAllByHotelId(Long hotelId);
}
