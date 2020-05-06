package com.lambdaschool.orders.services;


import com.lambdaschool.orders.models.Agent;

import java.util.List;

public interface AgentService
{
    Agent findAgentById(long id);
    List<Agent> findAll();

    void delete(long id);
}
