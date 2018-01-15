package com.almundo.callcenter.controller;

import com.almundo.callcenter.common.CallState;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;
import com.almundo.callcenter.service.impl.CallDispatcherImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CallCenterControllerTest {

    @InjectMocks
    private CallCenterController callCenterController;

    @Mock
    private CallDispatcherImpl callDispatcher;

    private Call call;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        callCenterController = new CallCenterController(callDispatcher);

        call = new Call();
        call.setUser(new User());
    }

    @Test
    public void shouldReceiveCallAndSendToDispatcher() {


        Mockito.doNothing().when(callDispatcher).receiveCall(call);

        callCenterController.receiveCall(call);

        assertEquals(CallState.OPEN, call.getState());
    }

    @Test
    public void shouldReceiveListOfCallsAndSendToDispatcher() {

        List<Call> callList = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(i -> callList.add(new Call(1, "test", new User())));

        Mockito.doNothing().when(callDispatcher).receiveCall(call);

        callList.forEach(c -> callCenterController.receiveCalls(callList));

        callList.forEach(c -> assertEquals(CallState.OPEN, c.getState()));
    }
}