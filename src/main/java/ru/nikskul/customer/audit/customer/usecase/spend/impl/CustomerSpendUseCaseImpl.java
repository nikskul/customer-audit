package ru.nikskul.customer.audit.customer.usecase.spend.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

import java.math.BigDecimal;

@Component
public class CustomerSpendUseCaseImpl implements CustomerSpendUseCase {

    private final CustomerRepository repository;

    public CustomerSpendUseCaseImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult spend(BigDecimal value) {
        repository.addSpend(value);
        return OperationResult.ok();
    }
}
