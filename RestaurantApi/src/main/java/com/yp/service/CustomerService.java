package com.yp.service;

import com.yp.dto.CustomerDto;
import com.yp.entity.Customer;
import com.yp.mapper.CustomerMapper;
import com.yp.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto getCustomer(Long id){
        if(id==null) return null;
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()) return null;
        return customerMapper.toDto(optionalCustomer.get());
    }

    public List<CustomerDto> getCustomerList(){
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public Page<CustomerDto> getCutomerPage(Pageable pageable){
        return customerRepository.findAllPages(pageable).map(customerMapper::toDto);
    }

    public Slice<CustomerDto> getCostomerSlice(Pageable pageable){
        return customerRepository.findAllSlices(pageable).map(customerMapper::toDto);
    }

    public String addCustomer(CustomerDto customerDto){
        if(customerDto==null) return "Failed";
        customerRepository.save(customerMapper.toEntity(customerDto));
        return "Success";
    }

    public String editCustomer(Long id, CustomerDto customerDto){
        if(id==null) return "Failed: no customer id given";
        if(customerDto==null) return "Failed: customer is empty";
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()){
            customerRepository.save(customerMapper.toEntity(customerDto));
            return "Added as new Customer";
        }
        Customer customer = optionalCustomer.get();
        String name = customerDto.getName();
        String surname = customerDto.getSurname();
        String phone = customerDto.getPhone();
        String address = customerDto.getAddress();
        if(!customer.getName().equals(name)) customer.setName(name);
        if(!customer.getSurname().equals(surname)) customer.setSurname(surname);
        if(!customer.getPhone().equals(phone)) customer.setPhone(phone);
        if(!customer.getAddress().equals(address)) customer.setAddress(address);
        customerRepository.save(customer);
        return "Successfully edited";
    }

    public String deleteCustomer(Long id){
        if(id==null) return "Failed: no id was given";
        customerRepository.deleteById(id);
        return "Successfully deleted";
    }

}
