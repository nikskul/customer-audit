package ru.nikskul.customer.audit.customer.spent.history.usecase.spend.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.spent.history.entity.CustomerSpentHistoryEntity;
import ru.nikskul.customer.audit.customer.spent.history.repository.CustomerSpentHistoryRepository;
import ru.nikskul.customer.audit.customer.spent.history.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

@Component
public class CustomerSpendUseCaseImpl implements CustomerSpendUseCase {

    private final CustomerSpentHistoryRepository repository;

    public CustomerSpendUseCaseImpl(CustomerSpentHistoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public OperationResult spend(SpendRequest spendRequest) {

        var entity = new CustomerSpentHistoryEntity();
        entity.setCustomerId(spendRequest.customerId());
        entity.setAmount(spendRequest.value());
        entity.setSpendTimestamp(spendRequest.timestamp());

        repository.saveAndFlush(entity);

        return OperationResult.ok();
    }
}
