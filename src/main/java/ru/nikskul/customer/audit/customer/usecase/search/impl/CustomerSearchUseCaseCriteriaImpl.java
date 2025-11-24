package ru.nikskul.customer.audit.customer.usecase.search.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.nikskul.customer.audit._itf.usecase.search.CriteriaSearch;
import ru.nikskul.customer.audit.customer.dto.converter._itf.CustomerDtoEntityConverter;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.entity.CustomerEntity;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.usecase.search._itf.CustomerSearchUseCase;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class CustomerSearchUseCaseCriteriaImpl
    extends CriteriaSearch<CustomerEntity, CustomerFilter>
    implements CustomerSearchUseCase {

    public static final String P_NAME = "p_name";

    private final CustomerDtoEntityConverter converter;

    public CustomerSearchUseCaseCriteriaImpl(
        EntityManager entityManager,
        CustomerDtoEntityConverter converter
    ) {
        super(entityManager);
        this.converter = converter;
    }

    @Override
    public List<CustomerDto> search(SearchParams<CustomerFilter> params) {
        return fetchResultList(params, CustomerEntity.class)
            .stream()
            .map(converter::toDto)
            .toList();
    }

    @Override
    protected List<Predicate> makeFilterPredicateList(
        SearchParams<CustomerFilter> params,
        CriteriaBuilder cb,
        Root<CustomerEntity> root
    ) {
        var filterPredicates = new ArrayList<Predicate>();
        if (params.filter().id() != null) {
            var id = cb.equal(root.get("id"), params.filter().id());
            filterPredicates.add(id);
        }
        if (params.filter().name() != null) {
            var fts = cb.function(
                "fts",
                Boolean.class,
                root.get("fullName"),
                cb.parameter(String.class, P_NAME)
            );
            filterPredicates.add(cb.isTrue(fts));
        }
        if (params.filter().phone() != null) {
            var phone = cb.equal(root.get("phone"), params.filter().phone());
            filterPredicates.add(phone);
        }
        return filterPredicates;
    }

    @Override
    protected Map<String, String> getParameters(
        SearchParams<CustomerFilter> searchParams
    ) {
        Map<String, String> resultMap = new HashMap<>();

        if (searchParams.filter() == null) return resultMap;
        var filter = searchParams.filter();

        if (filter.name() != null) {
            resultMap.put(P_NAME, filter.name());
        }

        return resultMap;
    }
}
