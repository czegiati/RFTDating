package hu.unideb.RFTDatingSite.Model.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
 public @interface LoginValidation {
        String message() default "Invalid login";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}

