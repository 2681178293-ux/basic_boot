package net.wanho.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerException extends RuntimeException {
    private ExceptionResult exceptionResult;
}
