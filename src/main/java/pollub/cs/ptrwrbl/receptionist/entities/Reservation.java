package pollub.cs.ptrwrbl.receptionist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pollub.cs.ptrwrbl.receptionist.validation.DepartureAfterArrival;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
@DepartureAfterArrival
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @NotNull(message = "Number of guests cannot be null")
    @Min(value = 1, message = "Number of guests must be at least 1")
    @Max(value = 16, message = "Number of guests cannot exceed 16")
    @Column
    private Integer numOfGuests;

    @NotNull(message = "Arrival date cannot be null")
    @Future(message = "Arrival date must be in the future")
    @Column
    private LocalDateTime arrival;

    @NotNull(message = "Departure date cannot be null")
    @Future(message = "Departure date must be in the future")
    @Column
    private LocalDateTime departure;
}