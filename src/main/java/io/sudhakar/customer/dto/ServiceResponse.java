package io.sudhakar.customer.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class ServiceResponse<T> {
    private T data;
    private HttpStatus httpStatus;
}
