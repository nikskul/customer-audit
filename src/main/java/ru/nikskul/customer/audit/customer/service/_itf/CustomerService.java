package ru.nikskul.customer.audit.customer.service._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.List;

public interface CustomerService {

    OperationResult create(CustomerDto customer);
    List<CustomerDto> listCustomer(SearchParams<CustomerFilter> searchParams);
    OperationResult changeName(CustomerDto customer);
}
