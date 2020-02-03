package br.com.task.domain.enumtype;

public enum StatusType {

    TODO("ToDo"),
    DOING("Doing"),
    DONE("Done"),
    REMOVED("Removed");

    private String description;

    StatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
