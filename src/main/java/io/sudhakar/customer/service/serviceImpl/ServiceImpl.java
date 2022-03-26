package io.sudhakar.customer.service.serviceImpl;

import io.sudhakar.customer.dto.Customer;
import io.sudhakar.customer.dto.ServiceResponse;
import io.sudhakar.customer.entity.CustomerEntity;
import io.sudhakar.customer.repository.CustomerRepository;
import io.sudhakar.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public ServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ServiceResponse<List<Customer>> getAll() {
        ServiceResponse<List<Customer>> serviceResponse = new ServiceResponse<>();

        try {
            log.info("method = getAll, status = IN_PROCESS");
            List<CustomerEntity> customerEntityList = (List<CustomerEntity>) customerRepository.findAll();

            List<Customer> customers = new ArrayList<>();

            for (CustomerEntity customerEntity : customerEntityList) {

                Customer customer = new Customer();
                BeanUtils.copyProperties(customerEntity, customer);
                customers.add(customer);
            }
            serviceResponse.setHttpStatus(HttpStatus.OK);
            serviceResponse.setData(customers);
            log.info("method = getAll, status = SUCCESS");
        } catch (Exception e) {
            log.error("method = getAll, status = ERROR");
            serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    public ServiceResponse<Customer> getById(int id) {
        ServiceResponse<Customer> serviceResponse = new ServiceResponse<>();
        try {
            log.info("method = getById, status = IN_PROCESS");

            Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerEntity.get(), customer);

            serviceResponse.setHttpStatus(HttpStatus.OK);
            serviceResponse.setData(customer);
            log.info("method = getById, status = SUCCESS");
        } catch (Exception e) {
            log.error("method = getById, status = SUCCESS");
            serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    public ServiceResponse<Void> add(Customer customer) {
        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        try {
            log.info("method = add, status = IN_SUCCESS");
            CustomerEntity customerEntity = new CustomerEntity();
            BeanUtils.copyProperties(customer, customerEntity);

            customerRepository.save(customerEntity);

            serviceResponse.setHttpStatus(HttpStatus.OK);
            log.info("method = add, status = SUCCESS");
        } catch (Exception e) {
            log.error("method = add, status = ERROR");
            serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResponse;
    }

    public ServiceResponse<Void> update(int id, Customer customer) {
        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();

        try {
            log.info("method = update, status = IN_PROCESS");

            Optional<CustomerEntity> customerEntity = customerRepository.findById(id);

            CustomerEntity customerEntity1 = customerEntity.get();

            BeanUtils.copyProperties(customer, customerEntity1);

//            customerEntity.get().setId(customer.getId());
//            customerEntity.get().setEmail(customer.getEmail());
//            customerEntity.get().setName(customer.getName());
//            customerEntity.get().setAddress(customer.getAddress());
//            customerEntity.get().setPhone_no(customer.getPhone_no());

            customerRepository.save(customerEntity1);
            serviceResponse.setHttpStatus(HttpStatus.OK);
            log.info("method = update, status = SUCCESS");
        } catch (Exception e) {
            serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("method = update, status = ERROR");
        }
        return serviceResponse;
    }

    public ServiceResponse<Void> delete(int id) {
        ServiceResponse<Void> serviceResponse = new ServiceResponse<>();
        try {
            log.info("method = delete, status = IN_PROCESS");

            customerRepository.deleteById(id);

            serviceResponse.setHttpStatus(HttpStatus.OK);
            log.info("method = delete, status = SUCCESS");
        } catch (Exception e) {
            serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            log.info("method = delete, status = ERROR");
        }
        return serviceResponse;
    }
}