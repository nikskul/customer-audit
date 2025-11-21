package ru.nikskul.customer.audit.customer.usecase.search._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.List;

public interface CustomerSearchUseCase {

    List<CustomerDto> search(SearchParams<CustomerFilter> params);
}
