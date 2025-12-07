package ru.nikskul.customer.audit.customer.usecase.create.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.create._itf.CustomerCreateUseCase;
import ru.nikskul.customer.audit.customer.usecase.validation._itf.CustomerValidationBeforeSaveUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.operation.result.impl.OperationResultCreate;

@Component
public class CustomerCreateUseCaseImpl implements CustomerCreateUseCase {

    private final CustomerRepository repository;
    private final CustomerDtoEntityConverter converter;
    private final CustomerValidationBeforeSaveUseCase validationBeforeSaveUseCase;

    public CustomerCreateUseCaseImpl(
            CustomerRepository repository,
            CustomerDtoEntityConverter converter,
            CustomerValidationBeforeSaveUseCase validationBeforeSaveUseCase
    ) {
        this.validationBeforeSaveUseCase = validationBeforeSaveUseCase;
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public OperationResultCreate exec(CustomerDto customerDto) {
        var validationResult = validationBeforeSaveUseCase.validate(customerDto, false);
        if (!validationResult.getStatus().equals(OperationResult.Status.OK)) {
            return OperationResultCreate.failed(validationResult.getMessage());
        }
        var entity = repository.save(converter.toEntity(customerDto));
        return OperationResultCreate.ok(entity.getId());
    }
}
