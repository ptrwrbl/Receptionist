package pollub.cs.ptrwrbl.receptionist.dtos;

import jakarta.persistence.Column;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
}
