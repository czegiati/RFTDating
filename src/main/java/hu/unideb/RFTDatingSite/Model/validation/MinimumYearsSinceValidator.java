package hu.unideb.RFTDatingSite.Model.validation;

import hu.unideb.RFTDatingSite.Model.DateFunctions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;

public class MinimumYearsSinceValidator implements ConstraintValidator<MinimumYearsSince, Date> {

    int min;
    @Override
    public void initialize(MinimumYearsSince constraintAnnotation) {
       min= constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if(value==null) return false;
        return DateFunctions.yearsPassedSince(value)>=min;
    }

}
