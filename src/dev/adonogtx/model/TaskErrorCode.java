package dev.adonogtx.model;

public enum TaskErrorCode {
    TITLE_REQUIRED("TASK_001", "Task title is required and cannot be empty."),
    DESCRIPTION_REQUIRED("TASK_002", "Task description cannot be null."),
    INVALID_DATE_FORMAT("TASK_003", "Invalid date format or value: '%s'."),
    INVALID_PRIORITY("TASK_004", "Invalid priority value: '%s'."),
    ALREADY_COMPLETED("TASK_005", "Task already completed."),
    TASK_NOT_FOUND("TASK_006", "Task with ID %s was not found.");

    private final String code;
    private final String defaultMessage;

    TaskErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }


    public String formatMessage(Object... args) {
        return String.format(defaultMessage, args);
    }
}
