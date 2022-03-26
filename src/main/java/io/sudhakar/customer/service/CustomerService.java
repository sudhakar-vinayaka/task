package io.sudhakar.customer.service;

import io.sudhakar.customer.dto.Customer;
import io.sudhakar.customer.dto.ServiceResponse;

import java.util.List;

public interface CustomerService {

    ServiceResponse<List<Customer>> getAll();

    ServiceResponse<Customer> getById(int id);

    ServiceResponse<Void> add(Customer customer);

    ServiceResponse<Void> update(int id,Customer customer);

    ServiceResponse<Void> delete(int id);
}
