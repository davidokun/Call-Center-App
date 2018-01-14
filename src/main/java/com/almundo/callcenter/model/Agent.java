package com.almundo.callcenter.model;

import com.almundo.callcenter.common.AgentRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.almundo.callcenter.common.CallState.FINISHED;
import static com.almundo.callcenter.common.CallState.IN_PROGRESS;

@Component
public class Agent {

    private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

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

        LOGGER.info( this.role + " No. " + this.id + " is attending Call No: " +  call.getId());

        call.setState(IN_PROGRESS);
        this.onCall = Boolean.TRUE;

        try {
            /* Simulates call duration between 5 and 10 seconds */
            Thread.sleep(new Random().nextInt((10000 - 5000) + 1) + 5000);

            call.setState(FINISHED);
            this.onCall = Boolean.FALSE;

            LOGGER.info("** " + this.role + " No. " + this.id + " Finish Call No: " +
                    call.getId() + " with user "+ call.getUser().getName()+" about " +
                    call.getSubject() + " **");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
