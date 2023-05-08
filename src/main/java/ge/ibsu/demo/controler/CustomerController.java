package ge.ibsu.demo.controler;

import ge.ibsu.demo.utils.GeneralUtil;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.request.RequestData;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@PreAuthorize("hasRole('ADMIN)")
public class CustomerController {

    @Autowired
    private CustomerService customerService;



    @PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = {"application/json"})
    public Customer getById (@PathVariable Long id) throws Exception{
        return  customerService.getById(id);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json"})
    public Customer getById(@RequestBody AddCustomer addCustomer) throws Exception{
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firsName","lastName"));
        return customerService.add(addCustomer);
    }
    @PreAuthorize("hasAuthority('customer:add')")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,produces ={"application/json"})
    public Customer edit(@PathVariable long id,@RequestBody AddCustomer addCustomer)throws Exception{
        GeneralUtil.checkRequiredProperties(addCustomer,Arrays.asList("firstName","lastName","addressId"));
        return customerService.edit(id,addCustomer);
    }
    @PreAuthorize("hasAuthority('customer:read')")
    @RequestMapping(value = "/search",method = RequestMethod.POST,produces = {"application/json"})
    public Slice<Customer> search(@RequestBody RequestData <SearchCustomer> rd){
        return customerService.search(rd.getData(),rd.getPaging());
    }
}
