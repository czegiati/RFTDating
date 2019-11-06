package hu.unideb.RFTDatingSite.Model.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
 public @interface UniqueUsername {
        String message() default "Invalid username";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}

