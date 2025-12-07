package ru.nikskul.customer.audit.customer.usecase.validation.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.usecase.validation._itf.CustomerValidationBeforeSaveUseCase;
import ru.nikskul.customer.audit.customer.util.phone.validation.impl.PhoneValidationUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

@Component
public class CustomerValidationBeforeSaveUseCaseImpl implements CustomerValidationBeforeSaveUseCase {

    private final PhoneValidationUseCase phoneValidationUseCase;

    public CustomerValidationBeforeSaveUseCaseImpl(PhoneValidationUseCase phoneValidationUseCase) {
        this.phoneValidationUseCase = phoneValidationUseCase;
    }

    @Override
    public OperationResult validate(CustomerDto customer, boolean requireId) {
        if (customer == null || (requireId && customer.id() == null)) {
            return OperationResult.failed("Не удалось идентифицировать клиента!");
        }
        if (!phoneValidationUseCase.isValid(customer.phone())) {
            return OperationResult.failed(
                    "Номер телефона имеет некорректный формат! Используйте формат: +70000000000"
            );
        }
        return OperationResult.ok();
    }
}
