package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AgentController
{
    @Autowired
    private AgentService agentService;

    // return all agents and their customers
    // GET - http://localhost:2019/agent/agents
    @GetMapping(value = "/agents",
                produces = {"application/json"})
    public ResponseEntity<?> listAllAgents()
    {
        List<Agent> rtnList = agentService.findAll();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    // delete an agent if they are not assigned to a customer
    // DELETE - http://localhost:2019/agent/{agentcode}
    @DeleteMapping("/{agentcode}")
    public ResponseEntity<?> deleteAgentById(@PathVariable
                                                     long agentcode)
    {
        agentService.delete(agentcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
