package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static com.almundo.callcenter.common.CallState.OPEN;

@RestController("v1")
public class CallCenterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallCenterController.class);

    private Dispatcher dispatcher;

    @Autowired
    public CallCenterController(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @PostMapping("call")
    public void receiveCall(@RequestBody Call call) {


        call.setId(new Random().nextInt(100));
        call.setState(OPEN);
        LOGGER.info("Call No: " + call.getId() + " Received in Call Center");
        sendCallToDispatcher(call);
    }

    @PostMapping("bulk/call")
    public void receiveCalls(@RequestBody List<Call> calls) {

        calls.forEach(call -> {
            call.setId(new Random().nextInt(100));
            call.setState(OPEN);
            LOGGER.info("Call No: " + call.getId() + " Received in Call Center");
            sendCallToDispatcher(call);
        });
    }


    /**
     * Send calls to CallDispatcher
     * @param call a call received in the Call Center
     */
    private void sendCallToDispatcher(Call call) {

        dispatcher.receiveCall(call);

    }
}
