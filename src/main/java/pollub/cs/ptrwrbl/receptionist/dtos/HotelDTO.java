package pollub.cs.ptrwrbl.receptionist.dtos;

import jakarta.validation.groups.ConvertGroup;
import lombok.*;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConvertGroup(from = Hotel.class, to = HotelDTO.class)
public class HotelDTO {
    private Long id;

    private String name;
    private String location;
    private String description;
    private String image;
}
