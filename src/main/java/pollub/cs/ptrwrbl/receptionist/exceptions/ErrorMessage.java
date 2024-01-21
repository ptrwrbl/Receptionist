package pollub.cs.ptrwrbl.receptionist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private Date timestamp;
    private int status;
    private List<String> errors;
    private String description;
}
