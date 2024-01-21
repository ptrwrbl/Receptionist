package pollub.cs.ptrwrbl.receptionist.exceptions;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(MessageFormat.format("Użytkownik {0} nie istnieje", message));
    }
}