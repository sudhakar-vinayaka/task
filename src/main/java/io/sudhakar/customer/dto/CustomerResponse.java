package io.sudhakar.customer.dto;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse<T> {
    private T data;
}
