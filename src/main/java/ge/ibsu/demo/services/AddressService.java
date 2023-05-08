package ge.ibsu.demo.services;
import java.util.List;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getALL(){
        return  addressRepository.findAll();
    }
    public Address getAddressById (Long id) throws  Exception{
        return addressRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT FOUND"));
    }

    public Address getById(Long id) throws Exception {

        return addressRepository.findById(id).orElseThrow(() -> new Exception("RECORD_NOT_FOUND"));
    }
}
