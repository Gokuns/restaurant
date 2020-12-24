package com.yp.controller;

import com.yp.dto.CustomerDto;
import com.yp.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Customer Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getCustomerList();
    }

    @GetMapping("/paged")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Page<CustomerDto> getCustomerPages(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.getCutomerPage(pageable);
    }
    @GetMapping("/slice")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Slice<CustomerDto> getCustomerSlice(Pageable pageable){
        return customerService.getCostomerSlice(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public CustomerDto getCusomer(@PathVariable(value = "id") Long id){
        return customerService.getCustomer(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editCustomer(@PathVariable(value = "id") Long id, @RequestBody CustomerDto customerDto){
        return customerService.editCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCustomer(@PathVariable(value = "id") Long id){
        return customerService.deleteCustomer(id);
    }

}
