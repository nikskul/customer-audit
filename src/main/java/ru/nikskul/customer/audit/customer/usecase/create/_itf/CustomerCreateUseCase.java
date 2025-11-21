package ru.nikskul.customer.audit.customer.usecase.create._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

public interface CustomerCreateUseCase {
    OperationResult exec(CustomerDto customerDto);
}
