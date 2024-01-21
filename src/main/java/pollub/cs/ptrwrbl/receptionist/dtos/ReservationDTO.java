package pollub.cs.ptrwrbl.receptionist.dtos;

import jakarta.validation.groups.ConvertGroup;
import lombok.*;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConvertGroup(from = Reservation.class, to = ReservationDTO.class)
public class ReservationDTO {
    private Long id;
    private Long roomId;
    private Long userId;

    private Integer numOfGuests;
    private LocalDateTime arrival;
    private LocalDateTime departure;
}
