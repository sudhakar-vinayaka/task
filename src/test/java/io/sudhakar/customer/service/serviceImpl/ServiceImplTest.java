package io.sudhakar.customer.service.serviceImpl;

import io.sudhakar.customer.dto.Customer;
import io.sudhakar.customer.dto.ServiceResponse;
import io.sudhakar.customer.entity.CustomerEntity;
import io.sudhakar.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest {

    ServiceImpl service;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ServiceImpl(customerRepository);
    }

    /*
    Given getAll(),findAll()
    Returns ServiceResponse,customerList
    Status Success
    */
    @Test
    void getAll() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress("address");
        customerEntity.setEmail("1@");
        customerEntity.setId(1);
        customerEntity.setName("name");
        customerEntity.setPhone_no(1234);

        List<CustomerEntity> customerList = new ArrayList<>();
        customerList.add(customerEntity);

        Mockito.when(customerRepository.findAll())
                .thenReturn(customerList);

        ServiceResponse<List<Customer>> serviceResponse = service.getAll();
        serviceResponse.setHttpStatus(HttpStatus.OK);

        assertEquals(HttpStatus.OK, serviceResponse.getHttpStatus());

        assertEquals(1, serviceResponse.getData().get(0).getId());
        assertEquals("name", serviceResponse.getData().get(0).getName());
    }

    /*
    Given getAll(),findAll()
    Returns ServiceResponse,customerList
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void getAllError() {
        Mockito.when(customerRepository.findAll())
                .thenThrow(new NullPointerException(""));

        ServiceResponse<List<Customer>> serviceResponse = service.getAll();

        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, serviceResponse.getHttpStatus());
    }

    /*
    Given getById(),findById()
    Returns ServiceResponse,customerList
    Status SUCCESS
    */
    @Test
    void getById() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress("address");
        customerEntity.setEmail("1@");
        customerEntity.setId(1);
        customerEntity.setName("name");
        customerEntity.setPhone_no(1234);

        Mockito.when(customerRepository.findById(1))
                .thenReturn(Optional.of(customerEntity));

        ServiceResponse<Customer> serviceResponse = service.getById(1);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        assertEquals(HttpStatus.OK, serviceResponse.getHttpStatus());

        assertEquals(1, serviceResponse.getData().getId());
        assertEquals("name", serviceResponse.getData().getName());
    }

    /*
    Given getById(),findById()
    Returns ServiceResponse,customerList
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void getByIdError() {
        Mockito.when(customerRepository.findById(1))
                .thenThrow(new NullPointerException(""));

        ServiceResponse<Customer> serviceResponse = service.getById(1);

        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, serviceResponse.getHttpStatus());
    }

    /*
    Given add(),save()
    Returns ServiceResponse,customerList
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

        Mockito.when(customerRepository.save(Mockito.any(CustomerEntity.class)))
                .thenReturn(null);

        ServiceResponse<Void> serviceResponse = service.add(customer);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        assertEquals(HttpStatus.OK, serviceResponse.getHttpStatus());
    }

    /*
    Given add(),save()
    Returns ServiceResponse,customerList
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void addError() {
        Mockito.when(customerRepository.save(Mockito.any(CustomerEntity.class)))
                .thenThrow(new NullPointerException(""));

        ServiceResponse<Void> serviceResponse = service.add(Mockito.any());
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, serviceResponse.getHttpStatus());
    }

    /*
    Given update(),save()
    Returns ServiceResponse,customerList
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

        Mockito.when(customerRepository.save(Mockito.any(CustomerEntity.class)))
                .thenReturn(null);

        ServiceResponse<Void> serviceResponse = service.update(1, customer);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        assertEquals(HttpStatus.OK, serviceResponse.getHttpStatus());
    }

    /*
    Given update(),save()
    Returns ServiceResponse,customerList
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void updateError() {
        Mockito.when(customerRepository.save(Mockito.any(CustomerEntity.class)))
                .thenThrow(new NullPointerException(""));

        ServiceResponse<Void> serviceResponse = service.update(1, Mockito.any(Customer.class));
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, serviceResponse.getHttpStatus());
    }

    /*
    Given delete(),deleteById()
    Returns ServiceResponse,customerList
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

        Mockito.doNothing().when(customerRepository).deleteById(1);

        ServiceResponse<Void> serviceResponse = service.delete(1);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        assertEquals(HttpStatus.OK, serviceResponse.getHttpStatus());
    }

    /*
    Given delete(),deleteById()
    Returns ServiceResponse,customerList
    Status INTERNAL_SERVER_ERROR
    */
    @Test
    void deleteError() {
        Mockito.doThrow(new NullPointerException(""))
                .when(customerRepository).deleteById(1);

        ServiceResponse<Void> serviceResponse = service.delete(1);
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, serviceResponse.getHttpStatus());
    }
}