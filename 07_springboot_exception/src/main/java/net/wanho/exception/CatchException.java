package net.wanho.exception;

import net.wanho.vo.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatchException {

    @ExceptionHandler(CustomerException.class)
    public AjaxResult catchCustomerException(CustomerException e){
        ExceptionResult result = e.getExceptionResult();
        return AjaxResult.error(result.code(),result.msg());
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult catchException(Exception e){
        return AjaxResult.error(e.getMessage());
    }
}
