package ru.nikskul.customer.audit.customer.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.service._itf.CustomerService;
import ru.nikskul.customer.audit.customer.service._itf.CustomerSpendService;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

import java.util.List;

@RestController
@CrossOrigin
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

    @PutMapping
    public OperationResult update(@RequestBody CustomerDto customer) {
        return service.update(customer);
    }

    @PatchMapping("/change-full-name")
    public OperationResult changeName(@RequestBody CustomerDto customerDto) {
        return service.changeName(customerDto);
    }

    @PostMapping("/add-spend")
    public OperationResult addSpent(@RequestBody SpendRequest request) {
        return spendService.spend(request);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomerDto>> search(
        @RequestBody SearchParams<CustomerFilter> params
    ) {
        return ResponseEntity.ok(service.listCustomer(params));
    }
}
