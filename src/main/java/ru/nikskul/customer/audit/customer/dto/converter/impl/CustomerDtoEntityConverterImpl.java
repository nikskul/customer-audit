package ru.nikskul.customer.audit.customer.dto.converter.impl;

import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;

@Component
public class CustomerDtoEntityConverterImpl
    implements CustomerDtoEntityConverter {

    @Override
    public CustomerEntity toEntity(CustomerDto dto) {
        CustomerEntity result = new CustomerEntity();
        result.setId(dto.id());
        result.setFullName(dto.fullName());
        result.setPhone(dto.phone());
        result.setSpent(dto.spent());
        return result;
    }

    @Override
    public CustomerDto toDto(CustomerEntity entity) {
        return new CustomerDto(
            entity.getId(),
            entity.getFullName(),
            entity.getPhone(),
            entity.getSpent()
        );
    }
}
