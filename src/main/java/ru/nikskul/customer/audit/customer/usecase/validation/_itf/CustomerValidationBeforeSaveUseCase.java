package ru.nikskul.customer.audit.customer.usecase.validation._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

public interface CustomerValidationBeforeSaveUseCase {
    OperationResult validate(CustomerDto customer, boolean requireId);
}
