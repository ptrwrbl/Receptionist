package pollub.cs.ptrwrbl.receptionist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class DetailedErrorDetails {
    private Date timestamp;
    private int status;
    private Map<String, String> message;
    private String description;
}
