package ru.nikskul.customer.audit.operation.result.impl;

import java.util.Objects;

public class OperationResultCreate extends OperationResult {

    private final Long id;

    public OperationResultCreate(Status status, String message) {
        super(status, message);
        this.id = null;
    }

    public OperationResultCreate(Long id, Status status, String message) {
        super(status, message);
        this.id = id;
    }

    public static OperationResultCreate ok(Long id) {
        return new OperationResultCreate(id, Status.OK, null);
    }

    public static OperationResultCreate failed(String message) {
        return new OperationResultCreate(Status.ERROR, message);
    }

    public Long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OperationResultCreate that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
