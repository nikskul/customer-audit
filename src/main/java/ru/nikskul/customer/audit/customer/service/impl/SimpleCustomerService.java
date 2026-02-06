package ru.nikskul.customer.audit.customer.service.impl;

import org.springframework.stereotype.Service;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.service._itf.CustomerService;
import ru.nikskul.customer.audit.customer.service._itf.CustomerSpendService;
import ru.nikskul.customer.audit.customer.spent.history.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.customer.usecase.change.name._itf.CustomerChangeNameUseCase;
import ru.nikskul.customer.audit.customer.usecase.create._itf.CustomerCreateUseCase;
import ru.nikskul.customer.audit.customer.usecase.search._itf.CustomerSearchUseCase;
import ru.nikskul.customer.audit.customer.usecase.update._itf.CustomerUpdateUseCase;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

import java.util.List;

@Service
public class SimpleCustomerService
    implements CustomerService, CustomerSpendService {

    private final CustomerCreateUseCase createUseCase;
    private final CustomerUpdateUseCase updateUseCase;
    private final CustomerSearchUseCase searchUseCase;
    private final CustomerChangeNameUseCase changeNameUseCase;
    private final CustomerSpendUseCase spendUseCase;

    public SimpleCustomerService(
            CustomerCreateUseCase createUseCase,
            CustomerUpdateUseCase updateUseCase,
            CustomerSearchUseCase searchUseCase,
            CustomerChangeNameUseCase changeNameUseCase,
            CustomerSpendUseCase spendUseCase
    ) {
        this.createUseCase = createUseCase;
        this.updateUseCase = updateUseCase;
        this.searchUseCase = searchUseCase;
        this.changeNameUseCase = changeNameUseCase;
        this.spendUseCase = spendUseCase;
    }

    @Override
    public OperationResult create(CustomerDto customer) {
        return createUseCase.exec(customer);
    }

    @Override
    public OperationResult update(CustomerDto customer) {
        return updateUseCase.exec(customer);
    }

    @Override
    public List<CustomerDto> listCustomer(SearchParams<CustomerFilter> searchParams) {
        return searchUseCase.search(searchParams);
    }

    @Override
    public OperationResult changeName(CustomerDto customer) {
        return changeNameUseCase.exec(customer);
    }

    @Override
    public OperationResult spend(SpendRequest spendRequest) {
        return spendUseCase.spend(spendRequest);
    }
}
