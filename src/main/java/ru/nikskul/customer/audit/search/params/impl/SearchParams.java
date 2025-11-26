package ru.nikskul.customer.audit.search.params.impl;

import java.util.HashMap;
import java.util.Map;

public record SearchParams<T>(
    T filter,
    long key,
    int limit,
    Map<String, String> sorting
) {
    public SearchParams(T filter) {
        this(filter, 0L, 10, new HashMap<>());
    }

    public SearchParams(T filter, long key, int limit) {
        this(filter, key, limit, new HashMap<>());
    }
}
