package com.yp.service;

import com.yp.dto.CustomerDto;
import com.yp.entity.Customer;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.CustomerMapper;
import com.yp.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private MessageSource messageSource;


    public CustomerDto getCustomer(Long id, String lang){
        if(id==null) throw new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isEmpty()) throw new ContentNotFoundException(messageSource.getMessage("ContentNotFound",new Object[0], new Locale(lang)));
        return customerMapper.toDto(optionalCustomer.get());
    }

    public List<CustomerDto> getCustomerList(String lang){
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public Page<CustomerDto> getCutomerPage(Pageable pageable, String lang){
        return customerRepository.findAllPages(pageable).map(customerMapper::toDto);
    }

    public Slice<CustomerDto> getCostomerSlice(Pageable pageable, String lang){
        return customerRepository.findAllSlices(pageable).map(customerMapper::toDto);
    }

    public String addCustomer(CustomerDto customerDto, String lang){
        if(customerDto==null) throw new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
        customerRepository.save(customerMapper.toEntity(customerDto));
        return "Success";
    }

    public String editCustomer(Long id, CustomerDto customerDto, String lang){
        if(id==null) throw  new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
        if(customerDto==null) throw new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
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

    public String deleteCustomer(Long id, String lang){
        if(id==null) throw new BusinessRuleException(messageSource.getMessage("BusinessRuleException", new Object[0], new Locale(lang)));
        customerRepository.deleteById(id);
        return "Successfully deleted";
    }

}
