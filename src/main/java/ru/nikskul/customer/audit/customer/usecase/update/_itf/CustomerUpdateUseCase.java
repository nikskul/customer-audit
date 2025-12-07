package ru.nikskul.customer.audit.customer.usecase.update._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

public interface CustomerUpdateUseCase {
    OperationResult exec(CustomerDto customer);
}
