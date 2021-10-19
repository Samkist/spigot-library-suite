package dev.samkist.core.storage.entities;

import dev.morphia.annotations.Id;

public abstract class Format {

    @Id
    String name;
    String permission;
    String messageFormat;
    Integer priority;

    public Format() {

    }

    public Format(String name, String permission, String messageFormat, Integer priority) {
        this.name = name;
        this.permission = permission;
        this.messageFormat = messageFormat;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}