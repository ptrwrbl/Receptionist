package pollub.cs.ptrwrbl.receptionist.dtos;

import jakarta.validation.groups.ConvertGroup;
import lombok.*;
import pollub.cs.ptrwrbl.receptionist.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ConvertGroup(from = User.class, to = UserDTO.class)
public class UserDTO {
    private String username;
    private String displayName;
    private String email;
    private String password;
}
