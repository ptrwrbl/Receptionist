package pollub.cs.ptrwrbl.receptionist.dtos;

import jakarta.validation.groups.ConvertGroup;
import lombok.*;
import pollub.cs.ptrwrbl.receptionist.entities.Room;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConvertGroup(from = Room.class, to = RoomDetailsDTO.class)
public class RoomDetailsDTO {
    private Long id;
    private Long hotelId;

    private String name;
    private Double price;
    private String description;
    private String image;
}
