package ru.nikskul.customer.audit.customer.spent.history.usecase.spend._itf;

import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

public interface CustomerSpendUseCase {
    OperationResult spend(SpendRequest spendRequest);
}
