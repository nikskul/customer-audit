package ru.nikskul.customer.audit.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import ru.nikskul.customer.audit._itf.entity.DomainEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "customer")
public class CustomerEntity extends DomainEntity {

    private String fullName;
    private String phone;
    private BigDecimal spent;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }
}
