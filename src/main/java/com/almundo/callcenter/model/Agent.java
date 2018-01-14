package com.almundo.callcenter.model;

import com.almundo.callcenter.common.AgentRole;
import org.springframework.stereotype.Component;

@Component
public class Agent {

    private int id;
    private AgentRole role;
    private Boolean onCall;

    public Agent() {
    }

    public Agent(int id, AgentRole role, Boolean onCall) {
        this.id = id;
        this.role = role;
        this.onCall = onCall;
    }

    public void attendCall(Call call) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgentRole getRole() {
        return role;
    }

    public void setRole(AgentRole role) {
        this.role = role;
    }

    public Boolean isOnCall() {
        return onCall;
    }

    public void setOnCall(Boolean onCall) {
        this.onCall = onCall;
    }
}
