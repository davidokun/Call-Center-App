package com.almundo.callcenter.controller;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1")
public class CallCenterController {

    @Autowired
    private Dispatcher dispatcher;

    @PostMapping("call")
    public void receiveCall(@RequestBody Call call) {

    }

    private void sendCallToDispatcher(Call call) {

        dispatcher.receiveCall(call);

    }
}
