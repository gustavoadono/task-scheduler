package dev.adonogtx.model;

public class TaskDomainException extends RuntimeException {
    private final TaskErrorCode errorCode;


    public TaskDomainException(TaskErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }


    public TaskDomainException(TaskErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.errorCode = errorCode;
    }

    public TaskErrorCode getErrorCode() {
        return errorCode;
    }
}
