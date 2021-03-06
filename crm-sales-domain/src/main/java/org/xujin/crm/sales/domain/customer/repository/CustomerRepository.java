package org.xujin.crm.sales.domain.customer.repository;

import org.xujin.crm.sales.tunnel.datatunnel.CustomerTunnelI;
import org.xujin.halo.repository.RepositoryI;
import org.xujin.crm.sales.domain.customer.convertor.CustomerConvertor;
import org.xujin.crm.sales.domain.customer.entity.CustomerE;
import org.xujin.crm.sales.tunnel.dataobject.CustomerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements RepositoryI{

    @Autowired
    private CustomerTunnelI customerDBTunnel;

    @Autowired
    private CustomerConvertor customerConvertor;

    public void persist(CustomerE customer) {
        customerDBTunnel.create(customerConvertor.entityToData(customer));
    }
    
    public List<CustomerE> findByCriteria(String... params){
        List<CustomerDO> customerDos = customerDBTunnel.findByCriteria(params);
        List<CustomerE> customerDs = new ArrayList<>();
        for (CustomerDO customerDo : customerDos) {
            customerDs.add(customerConvertor.dataToEntity(customerDo));
        }
        return customerDs;
    }
}
