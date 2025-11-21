package ru.nikskul.customer.audit.search.params.impl;

import java.util.HashMap;
import java.util.Map;

public class SearchParams<T> {

    private T filter;

    private long key = 0;
    private int limit = 10;

    private final Map<String, String> sorting = new HashMap<>();

    public SearchParams() {}
    public SearchParams(T filter, int key, int limit) {
        this.filter = filter;
        this.key = key;
        this.limit = limit;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Map<String, String> getSorting() {
        return sorting;
    }
}
