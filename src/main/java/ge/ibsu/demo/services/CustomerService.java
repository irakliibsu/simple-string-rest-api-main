package ge.ibsu.demo.services;

import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.request.Paging;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.dto.SearchCustomer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    AddressService addressService = new AddressService();



    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Customer getById(Long id) throws Exception{
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RECORD_NOT FOUND"));
    }

    public Customer add(AddCustomer addCustomer) throws Exception {
        Customer customer = new Customer();
        customer.setCreateDate(new Date());
//        customer.setFirstname(addCustomer.getFirstName());
//        customer.setLastName(addCustomer.getLastName());
//        customer.setActive(addCustomer.getActive());
        GeneralUtil.getCopyOf(addCustomer,customer);



        Address address = addressService.getById(addCustomer.getAddressId());

        customer.setAddress(address);
        return customerRepository.save(customer);

    }
    @Transactional
    public Customer edit(Long id,AddCustomer addCustomer) throws Exception {
        Customer customer = getById(id);
        GeneralUtil.getCopyOf(addCustomer,customer);
        if(addCustomer.getAddressId() !=null && !addCustomer.getAddressId().equals(customer.getAddress().getAddressId())){
            Address address = addressService.getById(addCustomer.getAddressId());
            customer.setAddress(address);
        }
        return customerRepository.save(customer);
    }
    public Slice<Customer> search(SearchCustomer searchCustomer, Paging paging){
        String searchText = null;
        if(searchCustomer.getName() !=null && !searchCustomer.equals("")){
            searchText = "%" + searchCustomer.getName() + "%";
        }
        Pageable pageable = PageRequest.of(paging.getPage(),paging.getSize(), Sort.by("create_date").descending());
        return  customerRepository.search(searchCustomer.getActive(),searchText, pageable);
    }

}
