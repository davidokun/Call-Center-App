package com.almundo.callcenter.service.impl;

import com.almundo.callcenter.common.AgentRole;
import com.almundo.callcenter.model.Agent;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.service.Dispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Dispatcher class implementation. Takes each recived call and send it to
 * any free operator. In case there is no operator free the call is attended by a Supervisor,
 * if Supervisor is busy then the Director takes the call.
 */
@Service
public class CallDispatcherImpl implements Dispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallDispatcherImpl.class);

    /* List of Operators in the call center */
    private Map<Integer, Agent> agentList = new HashMap<>();

    /* Executor Service to process the Calls Queue */
    private ExecutorService callsQueueExecutor = Executors.newFixedThreadPool(1);

    /* Executor Service with 10 Threads to manage agent's calls */
    private ExecutorService agentsExecutor = Executors.newFixedThreadPool(12);

    /* Individual Supervisor and Director objects */
    private Agent supervisor;
    private Agent director;

    /* A queue to hold all incoming calls*/
    private Queue<Call> callsQueue = new ConcurrentLinkedQueue<>();

    /**
     * This method register all agents in to the system
     */
    @PostConstruct
    private void registerAgents() {

        IntStream.rangeClosed(1, 10).forEach(i -> agentList.put(i, new Agent(i, AgentRole.OPERATOR, Boolean.FALSE)));

        supervisor = new Agent(11, AgentRole.SUPERVISOR, Boolean.FALSE);
        director = new Agent(12, AgentRole.DIRECTOR, Boolean.FALSE);

        processCallQueue();
    }

    /**
     * Has the responsability of looking for calls in the queue and dispatch calls to agents
     */
    private void processCallQueue() {

        callsQueueExecutor.submit(() -> {

            while (callsQueue.size() > -1){

                try {
                    /* Looks for call in queue every 5 seconds */
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    LOGGER.error("An error occurred in the Dispatcher", e);
                    Thread.currentThread().interrupt();
                }

                /* Search for free agents and dispatch the call */
                agentList.forEach((k,agent) -> {
                    if (!agent.isOnCall() && callsQueue.peek() != null) {
                        agent.setOnCall(Boolean.TRUE);
                        dispatchCall(agent, callsQueue.poll());
                    }
                });

                Stream<Map.Entry<Integer, Agent>> stream = agentList.entrySet().stream();

                /* If all operators are busy .. */
                boolean allMatch = stream.allMatch(v -> v.getValue().isOnCall());

                /* Check is Supervisor is free and asing call, otherwise dispatch to Director. */
                if (allMatch && !supervisor.isOnCall()) {
                    dispatchCall(supervisor, callsQueue.poll());
                }else {
                    dispatchCall(director, callsQueue.poll());
                }

                LOGGER.info("Calls in Queue: " + callsQueue.size());
            }

        });
    }

    /**
     * Receive calls for the controller
     * @param call a call received in the Call Center
     */
    @Override
    public void receiveCall(Call call) {
        callsQueue.add(call);

    }

    /**
     * Dispatch call to agents executor service
     * @param agent a free agent to attend the call.
     * @param call a call received in the Call Center
     */
    @Override
    public void dispatchCall(Agent agent, Call call) {

        agentsExecutor.submit(() -> agent.attendCall(call));
    }
}
