package pollub.cs.ptrwrbl.receptionist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String description;
}
