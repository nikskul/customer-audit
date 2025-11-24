package ru.nikskul.customer.audit.customer.usecase.search.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.search._itf.CustomerSearchUseCase;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerSearchUseCaseImpl implements CustomerSearchUseCase {

    private static final Logger log =
        LogManager.getLogger(CustomerSearchUseCaseImpl.class);
    private final CustomerRepository repository;
    private final CustomerDtoEntityConverter converter;
    private final ObjectMapper mapper;

    public CustomerSearchUseCaseImpl(
        CustomerRepository repository,
        CustomerDtoEntityConverter converter, ObjectMapper mapper
    ) {
        this.repository = repository;
        this.converter = converter;
        this.mapper = mapper;
    }

    @Override
    public List<CustomerDto> search(SearchParams<CustomerFilter> params) {
        try {
            String paramAsString = mapper.writeValueAsString(params);
            return repository.searchByParams(paramAsString).stream()
                .map(converter::toDto)
                .toList();
        } catch (JsonProcessingException e) {
            log.warn(e);
            return new ArrayList<>();
        }
    }
}
