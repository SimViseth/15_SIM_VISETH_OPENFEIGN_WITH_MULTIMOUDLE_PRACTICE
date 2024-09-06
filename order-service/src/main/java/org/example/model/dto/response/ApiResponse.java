package org.example.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
    private LocalDate localDate;
}
