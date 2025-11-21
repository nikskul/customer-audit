package ru.nikskul.customer.audit.customer.usecase.change.name._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

public interface CustomerChangeNameUseCase {

    OperationResult exec(CustomerDto customerDto);
}
