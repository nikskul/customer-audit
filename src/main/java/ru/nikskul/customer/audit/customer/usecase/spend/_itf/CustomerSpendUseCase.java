package ru.nikskul.customer.audit.customer.usecase.spend._itf;

import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

import java.math.BigDecimal;

public interface CustomerSpendUseCase {
    OperationResult spend(BigDecimal value);
}
