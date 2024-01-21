package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;
import pollub.cs.ptrwrbl.receptionist.entities.Room;
import pollub.cs.ptrwrbl.receptionist.entities.User;
import pollub.cs.ptrwrbl.receptionist.exceptions.ReservationNotFoundException;
import pollub.cs.ptrwrbl.receptionist.exceptions.RoomNotFoundException;
import pollub.cs.ptrwrbl.receptionist.exceptions.UserNotFoundException;
import pollub.cs.ptrwrbl.receptionist.mappers.ReservationMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.ReservationRepository;
import pollub.cs.ptrwrbl.receptionist.repositories.RoomRepository;
import pollub.cs.ptrwrbl.receptionist.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::reservationToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getRoomsReservations(Long roomId) {
        return reservationRepository.findAllByRoomId(roomId).stream()
                .map(reservationMapper::reservationToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getHotelsReservations(Long hotelId) {
        return reservationRepository.findAllByHotelId(hotelId).stream()
                .map(reservationMapper::reservationToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getUsersReservations(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream()
                .map(reservationMapper::reservationToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addReservation(ReservationDTO reservationDTO) {
        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(reservationDTO.getRoomId()));
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(reservationDTO.getUserId()));

        Reservation reservation = reservationMapper.reservationDTOToReservation(reservationDTO);
        reservation.setRoom(room);
        reservation.setUser(user);

        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::reservationToReservationDTO)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public void updateReservation(Long id, ReservationDTO reservationDTO) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));

        reservation.setNumOfGuests(reservationDTO.getNumOfGuests());
        reservation.setArrival(reservationDTO.getArrival());
        reservation.setDeparture(reservationDTO.getDeparture());

        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(reservationDTO.getRoomId()));
        reservation.setRoom(room);

        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(reservationDTO.getUserId()));
        reservation.setUser(user);

        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        reservationRepository.delete(reservation);
    }
}
