package br.com.zupacademy.robson.proposta.config.validation;

import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
public @interface ValidCpfOrCnpj {

    String message() default "Invalid document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
