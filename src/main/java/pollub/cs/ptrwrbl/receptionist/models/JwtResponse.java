package pollub.cs.ptrwrbl.receptionist.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
}