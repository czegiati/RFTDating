package hu.unideb.RFTDatingSite.Model.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinimumYearsSinceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
 public @interface MinimumYearsSince {
        public int min();
        String message() default "Invalid date";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}

