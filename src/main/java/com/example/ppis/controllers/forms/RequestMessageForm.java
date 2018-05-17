package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestMessageForm {

    private Long requestId;

    @Size(min = 3, max = 15)
    private String username;

    @Size(min = 1, max = 2000)
    private String message;

    @NotNull
    private String fromRole;

    @NotNull
    private String toRole;

    public RequestMessageForm() { }

    public RequestMessageForm(Long requestId, @Size(min = 3, max = 15) String username, @Size(min = 1, max = 2000) String message, @NotNull String fromRole, @NotNull String toRole) {
        this.requestId = requestId;
        this.username = username;
        this.message = message;
        this.fromRole = fromRole;
        this.toRole = toRole;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromRole() {
        return fromRole;
    }

    public void setFromRole(String fromRole) {
        this.fromRole = fromRole;
    }

    public String getToRole() {
        return toRole;
    }

    public void setToRole(String toRole) {
        this.toRole = toRole;
    }
}
