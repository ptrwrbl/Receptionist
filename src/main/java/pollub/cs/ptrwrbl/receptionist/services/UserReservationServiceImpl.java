package pollub.cs.ptrwrbl.receptionist.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pollub.cs.ptrwrbl.receptionist.config.JwtTokenUtil;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.ReservationDetailsDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;
import pollub.cs.ptrwrbl.receptionist.entities.Room;
import pollub.cs.ptrwrbl.receptionist.entities.User;
import pollub.cs.ptrwrbl.receptionist.exceptions.ReservationNotFoundException;
import pollub.cs.ptrwrbl.receptionist.exceptions.RoomNotFoundException;
import pollub.cs.ptrwrbl.receptionist.mappers.ReservationMapper;
import pollub.cs.ptrwrbl.receptionist.repositories.ReservationRepository;
import pollub.cs.ptrwrbl.receptionist.repositories.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserReservationServiceImpl implements UserReservationService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final UserServiceImpl userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<ReservationDetailsDTO> getAll(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.get(username);

        return reservationRepository.findAllByUserId(user.getId()).stream()
                .map(reservationMapper::reservationToReservationDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ReservationDTO reservationDTO, String token) {
        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(reservationDTO.getRoomId()));
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.get(username);

        Reservation reservation = reservationMapper.reservationDTOToReservation(reservationDTO);
        reservation.setRoom(room);
        reservation.setUser(user);

        reservationRepository.save(reservation);
    }

    @Override
    public ReservationDetailsDTO getOne(Long id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::reservationToReservationDetailsDTO)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public void update(Long id, ReservationDTO reservationDTO, String token) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));

        reservation.setNumOfGuests(reservationDTO.getNumOfGuests());
        reservation.setArrival(reservationDTO.getArrival());
        reservation.setDeparture(reservationDTO.getDeparture());

        Room room = roomRepository.findById(reservationDTO.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException(reservationDTO.getRoomId()));
        reservation.setRoom(room);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.get(username);
        reservation.setUser(user);

        reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
        reservationRepository.delete(reservation);
    }
}
