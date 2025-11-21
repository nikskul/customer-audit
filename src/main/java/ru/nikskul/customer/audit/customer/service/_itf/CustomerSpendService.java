package ru.nikskul.customer.audit.customer.service._itf;

import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

import java.math.BigDecimal;

public interface CustomerSpendService {

    OperationResult spend(BigDecimal value);
}
