package ru.nikskul.customer.audit.customer.spent.history.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.nikskul.customer.audit._itf.entity.DomainEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_spent_history")
public class CustomerSpentHistoryEntity extends DomainEntity {

    private Long customerId;
    private BigDecimal amount;
    private LocalDateTime spendTimestamp;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getSpendTimestamp() {
        return spendTimestamp;
    }

    public void setSpendTimestamp(LocalDateTime spendTimestamp) {
        this.spendTimestamp = spendTimestamp;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
