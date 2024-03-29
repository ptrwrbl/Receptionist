package pollub.cs.ptrwrbl.receptionist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pollub.cs.ptrwrbl.receptionist.entities.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotelId(Long hotelId);
}