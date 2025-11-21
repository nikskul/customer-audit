package ru.nikskul.customer.audit.customer.controller.impl;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.service._itf.CustomerSpendService;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;
import ru.nikskul.customer.audit.customer.service._itf.CustomerService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;
    private final CustomerSpendService spendService;

    public CustomerController(
        CustomerService service,
        CustomerSpendService spendService
    ) {
        this.service = service;
        this.spendService = spendService;
    }

    @PostMapping
    public OperationResult create(@RequestBody CustomerDto customer) {
        return service.create(customer);
    }

    @PatchMapping("/fullName")
    public OperationResult changeName(@RequestBody CustomerDto customerDto) {
        return service.changeName(customerDto);
    }

    @PostMapping("/add-spend")
    public OperationResult addSpent(@RequestBody BigDecimal value) {
        return spendService.spend(value);
    }

    @PostMapping("/search")
    public List<CustomerDto> search(SearchParams<CustomerFilter> params) {
        return service.listCustomer(params);
    }
}
