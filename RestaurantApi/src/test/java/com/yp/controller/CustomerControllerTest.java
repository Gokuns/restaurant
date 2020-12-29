package com.yp.controller;

import com.yp.builder.CustomerBuilder;
import com.yp.builder.CustomerDtoBuilder;
import com.yp.dto.CustomerDto;
import com.yp.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private final CustomerDto customerDto = new CustomerDtoBuilder().build();
    private final String lang = "en";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldGetAll(){
        customerController.getAllCustomers();
        verify(customerService,times(1)).getCustomerList();
    }

    @Test
    public void shouldGetPages(){
        customerController.getCustomerPages(0,1,lang);
        verify(customerService,times(1)).getCustomerPage(PageRequest.of(0,1), lang);
    }

    @Test
    public void shouldGetSlice(){
        customerController.getCustomerSlice(PageRequest.of(0,1), lang);
        verify(customerService,times(1)).getCustomerSlice(PageRequest.of(0,1), lang);
    }

    @Test
    public void shouldGet(){
        customerController.getCusomer(1L,lang);
        verify(customerService,times(1)).getCustomer(1L, lang);
    }

    @Test
    public void shouldAdd(){
        customerController.addCustomer(customerDto,lang);
        verify(customerService,times(1)).addCustomer(customerDto, lang);

    }

    @Test
    public void shouldEdit(){
        customerController.editCustomer(1L, customerDto,lang);
        verify(customerService,times(1)).editCustomer(1L, customerDto,  lang);

    }
    @Test
    public void shouldDelete(){
        customerController.deleteCustomer(1L,lang);
        verify(customerService,times(1)).deleteCustomer(1L, lang);

    }



}