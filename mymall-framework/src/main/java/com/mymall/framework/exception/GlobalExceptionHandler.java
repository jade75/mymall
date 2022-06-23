package com.mymall.framework.exception;


import com.mymall.framework.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        System.out.println("++++++++++++++++++");
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        log.error("路径不存在：----------------{}", e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库中已存在该记录：----------------{}", e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result handleException(RuntimeException e) {
        log.error("未知异常：----------------{}", e.getMessage(), e);
        return Result.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result handleException2(ArithmeticException e) {
        log.error("未知异常：----------------{}", e.getMessage(), e);
        return Result.error();
    }

    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        ObjectError objectError = result.getAllErrors().stream().findFirst().get();
        log.error("实体校验异常：----------------{}", objectError.getDefaultMessage());
        return Result.error(objectError.getDefaultMessage());
    }

    /**
     * 处理自定义异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(customizedException.class)
    public Result handleCustomException(customizedException e) {
        Result result = new Result();
        result.put("code", e.getCode());
        result.put("msg", e.getMessage());
        log.error("自定义异常：----------------{}", e.getMessage());
        return result;
    }

}
