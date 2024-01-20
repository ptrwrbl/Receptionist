package pollub.cs.ptrwrbl.receptionist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}