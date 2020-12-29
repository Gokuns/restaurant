package com.yp.service;

import com.yp.builder.*;
import com.yp.dto.CustomerDto;
import com.yp.entity.Customer;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.CustomerMapper;
import com.yp.repos.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private MessageSource messageSource;

    private final Customer customer = new CustomerBuilder().build();
    private final List<Customer> customerList = new ArrayList<>();
    private final CustomerDto customerDto = new CustomerDtoBuilder().build();
    private final List<CustomerDto> customerDtoList = new ArrayList<>();
    private final String lang = "en";


    @Before
    public void setUp() throws Exception {
        customerList.add(customer);
        customerDtoList.add(customerDto);
        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerRepository.findAllPages(any())).thenReturn( new PageBuilder<Customer>().build());
        when(customerRepository.findAllSlices(any())).thenReturn(new SliceBuilder<Customer>().build());
        when(messageSource.getMessage(any(), any(), any())).thenReturn("test");
        when(customerRepository.save(any())).thenReturn(customer);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(customer)).thenReturn(customerDto);
        when(customerMapper.toEntity(customerDto)).thenReturn(customer);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionWithId(){
        customerService.getCustomer(null, lang);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldRaiseExceptionWithNotFound(){
        customerService.getCustomer(2L, lang);
    }

    @Test
    public void shouldGetCustomer(){
        CustomerDto customer = customerService.getCustomer(1L, lang);
        assertNotNull(customer);
    }

    @Test
    public void shouldGetAllCustomers(){
        List<CustomerDto> customerList = customerService.getCustomerList();
        assertNotNull(customerList);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithPagable(){
        customerService.getCustomerPage(null, lang);
    }

    @Test
    public void shouldGetCustomerPage(){
        customerService.getCustomerPage(PageRequest.of(0,1), lang);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInGetWithSlice(){
        customerService.getCustomerSlice(null,lang);
    }

    @Test
    public void shouldGetCustomerSlice(){
        customerService.getCustomerSlice(PageRequest.of(0,1), lang);

    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionWithObject(){
        customerService.addCustomer(null, lang);
    }

    @Test
    public void shouldAddCustomer(){
        customerService.addCustomer(customerDto, lang);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithId(){
        customerService.editCustomer(null,customerDto,lang);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInEditWithObject(){
        customerService.editCustomer(1L, null, lang);
    }

    @Test
    public void shoulEditCustomer(){
        CustomerDto customerDto = new CustomerDtoBuilder().withId(2L).withName("test").withSurname("test")
                .withPhone("test").withAddress("test").withMedia(new MediaDtoBuilder().withName("test").build()).build();
        customerService.editCustomer(1L, customerDto, lang);
    }

    @Test
    public void shouldRaiseExceptionInEditWithNotFound(){
        customerService.editCustomer(2L, customerDto, lang);
        verify(customerRepository, times(1)).save(any());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldRaiseExceptionInDeleteWithId(){
        customerService.deleteCustomer(null, lang);
    }

    @Test
    public void shouldDeleteCustomer(){
        customerService.deleteCustomer(1L, lang);
        verify(customerRepository, times(1)).deleteById(1L);
    }





}