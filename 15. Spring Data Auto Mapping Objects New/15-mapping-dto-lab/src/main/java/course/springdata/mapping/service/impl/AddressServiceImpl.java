package course.springdata.mapping.service.impl;

import course.springdata.mapping.dao.AddressRepository;
import course.springdata.mapping.entity.Address;
import course.springdata.mapping.exception.NonexistingEntityException;
import course.springdata.mapping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepo;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepo.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Address with ID = %s does not exist.",id))
        );
    }

    // Because the @Transaction is by default readonly false, there is a flush
    // and commit at the end of
    // the transaction at the end of the method
    @Override
    @Transactional
    public Address addAddress(Address address) {
        address.setId(null);
        return addressRepo.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(Address address) {
        // If the entity exists it will be update, if not, there will be an exception
        getAddressById(address.getId());
        return addressRepo.save(address);
    }

    @Override
    @Transactional
    public Address deleteAddress(Long id) {
        Address removed = getAddressById(id);
        addressRepo.delete(removed);
        return removed;
    }

    @Override
    public long getAddressCount() {
        return addressRepo.count();
    }
}
