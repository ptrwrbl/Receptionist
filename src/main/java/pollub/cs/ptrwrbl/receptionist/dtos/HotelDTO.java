package pollub.cs.ptrwrbl.receptionist.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String image;
}
