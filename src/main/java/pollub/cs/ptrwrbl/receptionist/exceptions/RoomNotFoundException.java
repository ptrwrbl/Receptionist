package pollub.cs.ptrwrbl.receptionist.exceptions;

import java.text.MessageFormat;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Long id) {
        super(MessageFormat.format("Pokój {0} nie istnieje", id));
    }
}