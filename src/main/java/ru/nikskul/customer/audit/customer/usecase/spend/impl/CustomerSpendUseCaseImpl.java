package ru.nikskul.customer.audit.customer.usecase.spend.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

@Component
public class CustomerSpendUseCaseImpl implements CustomerSpendUseCase {

    private final CustomerRepository repository;

    public CustomerSpendUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult spend(SpendRequest spendRequest) {
        repository.addSpend(spendRequest.customerId(), spendRequest.value());
        return OperationResult.ok();
    }
}
