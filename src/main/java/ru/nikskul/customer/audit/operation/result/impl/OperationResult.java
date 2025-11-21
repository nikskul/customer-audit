package ru.nikskul.customer.audit.operation.result.impl;

public record OperationResult(
    Status status,
    String message
) {
    enum Status { OK, ERROR }

    public static OperationResult ok() {
        return new OperationResult(Status.OK, null);
    }

    public static OperationResult failed(String message) {
        return new OperationResult(Status.ERROR, message);
    }

}
