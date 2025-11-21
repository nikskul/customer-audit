package ru.nikskul.customer.audit.customer.util.phone.validation.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneValidationUseCase {

    private final Pattern phonePattern;

    public PhoneValidationUseCase(
        @Value("${phone.pattern}") String pattern
    ) {
        this.phonePattern = Pattern.compile(pattern);
    }

    public boolean isValid(String phone) {
        return phonePattern.matcher(phone).matches();
    }
}
