package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository custrepos;

    @Autowired
    private AgentService agentService;

    @Override
    public List<Customer> findAll()
    {
        List<Customer> rtnList = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(rtnList::add);

        return rtnList;
    }

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
//        newCustomer.setAgent(agentService.findAgentById(customer.getAgentcode()));
        newCustomer.setAgent(customer.getAgent());

        for (Order o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrddescription()));
        }

        return custrepos.save(newCustomer);
    }

    @Override
    public Customer update(Customer customer, long id)
    {
        return custrepos.save(customer);
    }

    @Override
    public void delete(long id)
    {
        custrepos.deleteById(id);
    }

}
