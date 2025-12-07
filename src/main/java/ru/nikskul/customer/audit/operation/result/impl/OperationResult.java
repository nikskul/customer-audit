package ru.nikskul.customer.audit.operation.result.impl;

import java.util.Objects;

public class OperationResult {
    private final Status status;
    private final String message;

    private static final OperationResult OK_STATUS = new OperationResult(
        Status.OK,
        null
    );

    public OperationResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static OperationResult ok() {
        return OK_STATUS;
    }

    public static OperationResult failed(String message) {
        return new OperationResult(Status.ERROR, message);
    }

    public enum Status {OK, ERROR}

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OperationResult that)) return false;
        return status == that.status && Objects.equals(
            message,
            that.message
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
