package com.almundo.callcenter.model;

import com.almundo.callcenter.common.CallState;

public class Call {

    private long id;
    private String subject;
    private User user;
    private CallState state;

    public Call() {
    }

    public Call(long id, String subject, User user) {
        this.id = id;
        this.subject = subject;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CallState getState() {
        return state;
    }

    public void setState(CallState state) {
        this.state = state;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
