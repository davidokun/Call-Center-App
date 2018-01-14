package com.almundo.callcenter.service;

import com.almundo.callcenter.model.Agent;
import com.almundo.callcenter.model.Call;

public interface Dispatcher {

    void receiveCall(Call call);

    void dispatchCall(Agent agent, Call call);
}
