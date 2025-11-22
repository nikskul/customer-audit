package ru.nikskul.customer.audit.customer.repository._itf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepository
    extends JpaRepository<CustomerEntity, Long> {

    @NativeQuery("SELECT * from customer_search(:params)")
    List<CustomerEntity> searchByParams(SearchParams<CustomerFilter> params);

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
