package pollub.cs.ptrwrbl.receptionist.exceptions;

import java.text.MessageFormat;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long id) {
        super(MessageFormat.format("Rezerwacja {0} nie istnieje", id));
    }
}