package net.wanho.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import net.wanho.vo.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatchException {

    @ExceptionHandler(NotPermissionException.class)
    public AjaxResult catchNotPermissionException(NotPermissionException e){
        return AjaxResult.error("没有权限，请联系管理！");
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult catchException(Exception e){
        return AjaxResult.error(e.getMessage());
    }
}
