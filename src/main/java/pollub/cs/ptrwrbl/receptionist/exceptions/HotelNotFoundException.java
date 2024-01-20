package pollub.cs.ptrwrbl.receptionist.exceptions;

import java.text.MessageFormat;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) { super(MessageFormat.format("Hotel {0} nie istnieje", id)); }
}
