package ru.nikskul.customer.audit.customer.usecase.search.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.search._itf.CustomerSearchUseCase;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.List;

@Component
public class CustomerSearchUseCaseImpl implements CustomerSearchUseCase {

    private final CustomerRepository repository;
    private final CustomerDtoEntityConverter converter;

    public CustomerSearchUseCaseImpl(
        CustomerRepository repository,
        CustomerDtoEntityConverter converter
    ) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<CustomerDto> search(SearchParams<CustomerFilter> params) {
        return repository.searchByParams(params).stream()
            .map(converter::toDto)
            .toList();
    }
}
