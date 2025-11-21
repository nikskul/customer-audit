package ru.nikskul.customer.audit.customer.usecase.create.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.create._itf.CustomerCreateUseCase;
import ru.nikskul.customer.audit.customer.util.phone.validation.impl.PhoneValidationUseCase;

@Component
public class CustomerCreateUseCaseImpl implements CustomerCreateUseCase {

    private final CustomerRepository repository;
    private final CustomerDtoEntityConverter converter;
    private final PhoneValidationUseCase phoneValidationUseCase;

    public CustomerCreateUseCaseImpl(
        PhoneValidationUseCase phoneValidationUseCase,
        CustomerRepository repository,
        CustomerDtoEntityConverter converter
    ) {
        this.phoneValidationUseCase = phoneValidationUseCase;
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public OperationResult exec(CustomerDto customerDto) {
        if (!phoneValidationUseCase.isValid(customerDto.phone())) {
            return OperationResult.failed("Phone number incorrect!");
        }
        repository.save(converter.toEntity(customerDto));
        return OperationResult.ok();
    }
}
