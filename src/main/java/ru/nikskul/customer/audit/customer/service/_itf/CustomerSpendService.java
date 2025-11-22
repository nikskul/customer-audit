package ru.nikskul.customer.audit.customer.service._itf;

import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

public interface CustomerSpendService {

    OperationResult spend(SpendRequest spendRequest);
}
