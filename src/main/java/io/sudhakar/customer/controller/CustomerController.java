package io.sudhakar.customer.controller;

import io.sudhakar.customer.dto.Customer;
import io.sudhakar.customer.dto.CustomerResponse;
import io.sudhakar.customer.dto.ServiceResponse;
import io.sudhakar.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    ResponseEntity<CustomerResponse<List<Customer>>> getAll() {

        log.info("api = /customer, method = GET, status = IN_PROCESS");

        ServiceResponse<List<Customer>> serviceResponse = customerService.getAll();

        log.info("api = /customer, method = GET, status = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new CustomerResponse<>(serviceResponse.getData()));
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse<Customer>> getById(@PathVariable int id) {

        log.info("api = /customer, method = GET, status = IN_PROCESS");

        ServiceResponse<Customer> serviceResponse = customerService.getById(id);

        log.info("api = /customer, method = GET, status = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).body(new CustomerResponse<>(serviceResponse.getData()));
    }

    @PostMapping
    ResponseEntity<ServiceResponse<Void>> add(@RequestBody Customer customer) {
        log.info("api = /customer, method = POST, status = IN_PROCESS");

        ServiceResponse<Void> serviceResponse = customerService.add(customer);

        log.info("api = /customer, method = POST, status = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ServiceResponse<Void>> add(@RequestBody Customer customer,@PathVariable int id) {
        log.info("api = /customer, method = POST, status = IN_PROCESS");

        ServiceResponse<Void> serviceResponse = customerService.update(id,customer);

        log.info("api = /customer, method = POST, status = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ServiceResponse<Void>> delete(@PathVariable int id) {
        log.info("api = /customer, method = DELETE, status = IN_PROCESS");

        ServiceResponse<Void> serviceResponse = customerService.delete(id);

        log.info("api = /customer, method = DELETE, status = SUCCESS");
        return ResponseEntity.status(serviceResponse.getHttpStatus()).build();
    }
}
