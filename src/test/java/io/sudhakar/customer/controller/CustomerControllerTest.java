package io.sudhakar.customer.controller;

import io.sudhakar.customer.dto.Customer;
import io.sudhakar.customer.dto.CustomerResponse;
import io.sudhakar.customer.dto.ServiceResponse;
import io.sudhakar.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    /*
    Given getAll()
    Returns ServiceResponse,customerList
    Status Success
    */
    @Test
    void getAll() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        ServiceResponse<List<Customer>> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(customerList);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        Mockito.when(customerService.getAll())
                .thenReturn(serviceResponse);

        ResponseEntity<CustomerResponse<List<Customer>>> responseEntity = customerController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(1, responseEntity.getBody().getData().get(0).getId());
        assertEquals("name", responseEntity.getBody().getData().get(0).getName());
    }

    /*
    Given getAll()
    Returns ServiceResponse
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void getAllError() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        ServiceResponse<List<Customer>> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(customerList);
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(customerService.getAll())
                .thenReturn(serviceResponse);

        ResponseEntity<CustomerResponse<List<Customer>>> responseEntity = customerController.getAll();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /*
    Given getById()
    Returns ServiceResponse,customer
    Status Success
    */
    @Test
    void getById() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Customer> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(customer);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        Mockito.when(customerService.getById(1))
                .thenReturn(serviceResponse);

        ResponseEntity<CustomerResponse<Customer>> responseEntity = customerController.getById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(1, responseEntity.getBody().getData().getId());
        assertEquals("name", responseEntity.getBody().getData().getName());
    }

    /*
    Given getById()
    Returns ServiceResponse
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void getByIdError() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Customer> serviceResponse = new ServiceResponse<>();
        serviceResponse.setData(customer);
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);


        Mockito.when(customerService.getById(1))
                .thenReturn(serviceResponse);

        ResponseEntity<CustomerResponse<Customer>> responseEntity = customerController.getById(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

    /*
    Given add()
    Returns ServiceResponse
    Status Success
    */
    @Test
    void add() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.OK);

        Mockito.when(customerService.add(customer))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.add(customer);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /*
    Given add()
    Returns ServiceResponse
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void addError() {

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(customerService.add(any()))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.add(any());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /*
    Given update()
    Returns ServiceResponse
    Status Success
    */
    @Test
    void update() {

        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.OK);

        Mockito.when(customerService.update(1, customer))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.update(customer, 1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /*
    Given update()
    Returns ServiceResponse
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void updateError() {

        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(customerService.update(1, customer))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.update(customer, 1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    /*
    Given delete()
    Returns ServiceResponse
    Status Success
    */
    @Test
    void delete() {
        Customer customer = new Customer();
        customer.setAddress("address");
        customer.setEmail("1@");
        customer.setId(1);
        customer.setName("name");
        customer.setPhone_no(1234);

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.OK);

        Mockito.when(customerService.delete(1))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.delete(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    /*
    Given delete()
    Returns ServiceResponse
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void deleteError() {

        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(customerService.delete(1))
                .thenReturn(serviceResponse);

        ResponseEntity<ServiceResponse<Void>> responseEntity = customerController.delete(1);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}