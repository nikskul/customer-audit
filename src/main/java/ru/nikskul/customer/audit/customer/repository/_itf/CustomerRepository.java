package ru.nikskul.customer.audit.customer.repository._itf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepository
    extends JpaRepository<CustomerEntity, Long> {

    @NativeQuery("SELECT * FROM customer_search((:params)::json)")
    List<CustomerEntity> searchByParams(@Param("params") String params);

    @Query("""
         UPDATE CustomerEntity e
         SET e.fullName = ?#{dto.fullName()}
         WHERE e.id = ?#{dto.id()}
        """)
    @Modifying
    void changeName(@Param("dto") CustomerDto dto);

    @Query("""
         UPDATE CustomerEntity e
         SET e.spent = e.spent + :value
         WHERE e.id = :id
        """)
    @Modifying
    void addSpend(Long id, BigDecimal value);
}
