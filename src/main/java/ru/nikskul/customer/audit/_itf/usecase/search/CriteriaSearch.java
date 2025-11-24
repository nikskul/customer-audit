package ru.nikskul.customer.audit._itf.usecase.search;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.nikskul.customer.audit._itf.entity.DomainEntity;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CriteriaSearch<T extends DomainEntity, Filter> {

    private final EntityManager em;

    protected CriteriaSearch(EntityManager em) {
        this.em = em;
    }

    public List<T> fetchResultList(SearchParams<Filter> params, Class<T> clazz) {
        var cb = em.getCriteriaBuilder();
        var q = cb.createQuery(clazz);
        var root = q.from(clazz);

        List<Predicate> predicates = makePredicate(params, cb, root);
        List<Order> order = makeOrderList(params, cb, root);

        TypedQuery<T> query = em.createQuery(q
            .where(predicates.toArray(Predicate[]::new))
            .orderBy(order)
        );

        setParameters(query, params);

        return query
            .setMaxResults(params.limit())
            .getResultList();
    }

    private List<Predicate> makePredicate(
        SearchParams<Filter> params,
        CriteriaBuilder cb,
        Root<T> root
    ) {
        var keyPredicate = cb.gt(root.get("id"), params.key());
        List<Predicate> predicates = makeFilterPredicateList(params, cb, root);
        predicates.add(keyPredicate);
        return predicates;
    }

    protected abstract List<Predicate> makeFilterPredicateList(
        SearchParams<Filter> params,
        CriteriaBuilder cb,
        Root<T> root
    );

    protected List<Order> makeOrderList(
        SearchParams<Filter> params,
        CriteriaBuilder cb,
        Root<T> root
    ) {
        if (params.sorting() == null || params.sorting().isEmpty()) {
            return new ArrayList<>();
        }

        List<Order> resultList = new ArrayList<>();
        for (var sort : params.sorting().entrySet()) {
            Order order;
            var path = root.get(sort.getKey());
            var direction = sort.getValue();
            if (direction.equalsIgnoreCase("desc")) {
                order = cb.desc(path);
            } else {
                order = cb.asc(path);
            }
            resultList.add(order);
        }
        return resultList;
    }

    private void setParameters(
        TypedQuery<T> query,
        SearchParams<Filter> params
    ) {
        Map<String, String> parameters = getParameters(params);
        if (parameters != null) {
            for (var param : parameters.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
    }

    protected abstract Map<String, String> getParameters(
        SearchParams<Filter> searchParams
    );
}
