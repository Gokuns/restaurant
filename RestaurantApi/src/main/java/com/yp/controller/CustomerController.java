package com.yp.controller;

import com.yp.dto.CustomerDto;
import com.yp.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Api(tags = "Customer Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<CustomerDto> getAllCustomers(@RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.getCustomerList(lang);
    }

    @GetMapping("/paged")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Page<CustomerDto> getCustomerPages(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(value = "lang", defaultValue = "en") String lang){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.getCutomerPage(pageable, lang);
    }
    @GetMapping("/slice")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Slice<CustomerDto> getCustomerSlice(Pageable pageable, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.getCostomerSlice(pageable, lang);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public CustomerDto getCusomer(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.getCustomer(id, lang);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String addCustomer(@RequestBody CustomerDto customerDto, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.addCustomer(customerDto, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editCustomer(@PathVariable(value = "id") Long id, @RequestBody CustomerDto customerDto, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.editCustomer(id, customerDto, lang);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCustomer(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return customerService.deleteCustomer(id, lang);
    }

    @GetMapping("/greet")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getGreeting(){
        return messageSource.getMessage("greeting", new Object[0], new Locale("tr"));
    }

    @GetMapping("/greeting")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public String getGreetings(@RequestParam(value = "lang", defaultValue = "en") String lang){
        return messageSource.getMessage("greeting", new Object[0], new Locale(lang));
    }
}
