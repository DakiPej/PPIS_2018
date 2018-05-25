package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageForm {

    private Long requestOrIncidentId;

    @Size(min = 3, max = 15)
    private String username;

    @Size(min = 1, max = 2000)
    private String message;

    @NotNull
    private String senderRole;

    @NotNull
    private String receiverRole;

    public MessageForm() { }

    public MessageForm(Long requestOrIncidentId, @Size(min = 3, max = 15) String username, @Size(min = 1, max = 2000) String message, @NotNull String senderRole, @NotNull String receiverRole) {
        this.requestOrIncidentId = requestOrIncidentId;
        this.username = username;
        this.message = message;
        this.senderRole = senderRole;
        this.receiverRole = receiverRole;
    }

    public Long getRequestOrIncidentId() {
        return requestOrIncidentId;
    }

    public void setRequestOrIncidentId(Long requestId) {
        this.requestOrIncidentId = requestId;
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

    public String getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(String senderRole) {
        this.senderRole = senderRole;
    }

    public String getReceiverRole() {
        return receiverRole;
    }

    public void setReceiverRole(String receiverRole) {
        this.receiverRole = receiverRole;
    }
}
