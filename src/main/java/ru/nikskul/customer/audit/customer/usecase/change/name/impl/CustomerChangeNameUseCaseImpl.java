package ru.nikskul.customer.audit.customer.usecase.change.name.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.change.name._itf.CustomerChangeNameUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

@Component
public class CustomerChangeNameUseCaseImpl
    implements CustomerChangeNameUseCase {

    private final CustomerRepository repository;

    public CustomerChangeNameUseCaseImpl(
        CustomerRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public OperationResult exec(CustomerDto customerDto) {
        repository.changeName(customerDto);
        return OperationResult.ok();
    }
}
