package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.common.AgentRole;
import com.almundo.callcenter.common.CallState;
import com.almundo.callcenter.model.Agent;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.fieldIn;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CallDispatcherImplTest {

    private CallDispatcherImpl callDispatcher;

    @Before
    public void setUp() {
        callDispatcher = new CallDispatcherImpl();
        callDispatcher.registerAgents();
    }


    @Test
    public void shouldProcessACallFromQueue() {

        Agent agent = new Agent();
        agent.setId(1);
        agent.setRole(AgentRole.OPERATOR);

        Call call = new Call();
        call.setState(CallState.OPEN);
        call.setUser(new User());

        callDispatcher.receiveCall(call);

        await().timeout(15000, TimeUnit.SECONDS)
                .until( fieldIn(call).ofType(CallState.class).andWithName("state"),
                equalTo(CallState.FINISHED) );

        assertEquals(CallState.FINISHED, call.getState());


    }

}