package ru.nikskul.customer.audit.customer.usecase.create._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResultCreate;

public interface CustomerCreateUseCase {
    OperationResultCreate exec(CustomerDto customerDto);
}
