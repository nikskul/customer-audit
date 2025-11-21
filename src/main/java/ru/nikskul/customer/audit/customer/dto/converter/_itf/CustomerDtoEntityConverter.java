package ru.nikskul.customer.audit.customer.dto.converter._itf;

import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;

public interface CustomerDtoEntityConverter {
    CustomerEntity toEntity(CustomerDto dto);
    CustomerDto toDto(CustomerEntity entity);
}
