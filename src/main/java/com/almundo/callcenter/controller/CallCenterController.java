package com.almundo.callcenter.controller;

import com.almundo.callcenter.common.CallCenterResponse;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Value("${callcenter.response.message}")
    private String callMessageResponse;

    @Autowired
    public CallCenterController(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Endpoint to receive 1 call
     * @param call a call received from a user
     */
    @PostMapping("call")
    public ResponseEntity<CallCenterResponse> receiveCall(@RequestBody Call call) {


        call.setId(new Random().nextInt(100));
        call.setState(OPEN);
        LOGGER.info("Call No: " + call.getId() + " Received in Call Center");
        sendCallToDispatcher(call);

        CallCenterResponse callCenterResponse = new CallCenterResponse(HttpStatus.ACCEPTED.value(),
                callMessageResponse);

        return ResponseEntity.accepted().body(callCenterResponse);
    }

    /**
     * Endpoint to receive multiple calls at once
     * @param calls list of calls
     */
    @PostMapping("call/bulk")
    public  ResponseEntity<CallCenterResponse> receiveCalls(@RequestBody List<Call> calls) {

        calls.forEach(call -> {
            call.setId(new Random().nextInt(100));
            call.setState(OPEN);
            LOGGER.info("Call No: " + call.getId() + " Received in Call Center");
            sendCallToDispatcher(call);
        });

        CallCenterResponse callCenterResponse = new CallCenterResponse(HttpStatus.ACCEPTED.value(),
                callMessageResponse);

        return ResponseEntity.accepted().body(callCenterResponse);
    }


    /**
     * Send calls to CallDispatcher
     * @param call a call received in the Call Center
     */
    private void sendCallToDispatcher(Call call) {

        dispatcher.receiveCall(call);

    }
}
