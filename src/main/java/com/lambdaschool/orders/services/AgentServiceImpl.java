package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    private AgentRepository agentrepos;

    @Override
    public List<Agent> findAll()
    {
        List<Agent> rtnList = new ArrayList<>();

        agentrepos.findAll().iterator().forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Override
    public Agent findAgentById(long id) throws EntityNotFoundException
    {
        return agentrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Transactional
    @Override
    public void delete(long id)
    {
        if (agentrepos.findById(id).get().getCustomers().size() == 0)
        {
            agentrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Agent is assigned to a customer");
        }
    }
}
