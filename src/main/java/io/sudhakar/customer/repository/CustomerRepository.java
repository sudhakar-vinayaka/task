package io.sudhakar.customer.repository;

import io.sudhakar.customer.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {

}
