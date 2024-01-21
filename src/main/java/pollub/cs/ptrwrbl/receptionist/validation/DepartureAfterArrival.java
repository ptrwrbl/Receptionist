package pollub.cs.ptrwrbl.receptionist.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartureAfterArrivalValidator.class)
public @interface DepartureAfterArrival {
    String message() default "Departure date must be after arrival date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}