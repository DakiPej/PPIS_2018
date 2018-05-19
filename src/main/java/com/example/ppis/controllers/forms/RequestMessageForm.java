package com.example.ppis.controllers.forms;

import javax.validation.constraints.Size;

public class RequestMessageForm {

    private Long requestId;

    @Size(min = 3, max = 15)
    private String username;

    @Size(max = 2000)

    private String message;

    public RequestMessageForm() {
    }

    public RequestMessageForm(Long requestId, String username, String message) {
        this.requestId = requestId;
        this.username = username;
        this.message = message;
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
}
