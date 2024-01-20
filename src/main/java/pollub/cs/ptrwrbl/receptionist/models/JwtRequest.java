package pollub.cs.ptrwrbl.receptionist.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;
}