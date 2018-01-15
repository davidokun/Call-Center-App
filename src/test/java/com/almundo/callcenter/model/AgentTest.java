package com.almundo.callcenter.model;

import com.almundo.callcenter.common.AgentRole;
import com.almundo.callcenter.common.CallState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class AgentTest {

    private Agent agent;

    private Call call;

    @Before
    public void setUp() {

        agent = new Agent();
        agent.setId(1);
        agent.setRole(AgentRole.OPERATOR);

        call = new Call();
        call.setState(CallState.OPEN);
        call.setUser(new User());
    }

    @Test
    public void shouldAttendCall() {

        agent.attendCall(call);
        assertEquals(CallState.FINISHED, call.getState());
    }

}