package ru.nikskul.customer.audit.customer.usecase.update.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.update._itf.CustomerUpdateUseCase;
import ru.nikskul.customer.audit.customer.usecase.validation._itf.CustomerValidationBeforeSaveUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

@Component
public class CustomerUpdateUseCaseImpl implements CustomerUpdateUseCase {

    private final CustomerRepository repository;
    private final CustomerValidationBeforeSaveUseCase validationBeforeSaveUseCase;

    public CustomerUpdateUseCaseImpl(
            CustomerRepository repository,
            CustomerValidationBeforeSaveUseCase validationBeforeSaveUseCase
    ) {
        this.repository = repository;
        this.validationBeforeSaveUseCase = validationBeforeSaveUseCase;
    }

    @Override
    public OperationResult exec(CustomerDto customer) {
        var validationResult = validationBeforeSaveUseCase.validate(customer, true);
        if (!validationResult.getStatus().equals(OperationResult.Status.OK)) {
            return validationResult;
        }

        var entityOpt = repository.findById(customer.id());

        if (entityOpt.isEmpty()) {
            return OperationResult.failed("Не удалось найти пользователя с id = " + customer.id());
        }

        var entity = entityOpt.get();

        mapFields(customer, entity);

        repository.save(entity);

        return OperationResult.ok();
    }

    private void mapFields(CustomerDto customer, CustomerEntity entity) {
        entity.setFullName(customer.fullName());
        entity.setPhone(customer.phone());
    }
}
