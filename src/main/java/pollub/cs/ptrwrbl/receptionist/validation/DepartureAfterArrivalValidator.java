package pollub.cs.ptrwrbl.receptionist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pollub.cs.ptrwrbl.receptionist.entities.Reservation;

public class DepartureAfterArrivalValidator implements ConstraintValidator<DepartureAfterArrival, Reservation> {
    @Override
    public void initialize(DepartureAfterArrival constraintAnnotation) {
    }

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        if (!reservation.getDeparture().isAfter(reservation.getArrival())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Departure date must be after arrival date")
                    .addPropertyNode("departure").addConstraintViolation();
            return false;
        }
        return true;
    }
}
