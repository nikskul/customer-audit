package ru.nikskul.customer.audit.customer.spent.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikskul.customer.audit.customer.spent.history.entity.CustomerSpentHistoryEntity;

public interface CustomerSpentHistoryRepository extends JpaRepository<CustomerSpentHistoryEntity, Long> {

}
