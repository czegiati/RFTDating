package hu.unideb.RFTDatingSite.Model.validation;

import hu.unideb.RFTDatingSite.Model.DateFunctions;
import hu.unideb.RFTDatingSite.Model.forms.UserLoginForm;
import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

public class LoginValidator implements ConstraintValidator<LoginValidation, UserLoginForm> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(LoginValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserLoginForm value, ConstraintValidatorContext context) {
        return userService.correctLogIn(value.getUsername(),value.getPassword());
    }
}
