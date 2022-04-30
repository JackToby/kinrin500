package com.kinrin500.aspect;


import com.kinrin500.util.Result;
import com.kinrin500.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * 处理全局异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public <T> Result<T> handle(Exception e){
        log.error("发生未处理异常={}",e.getMessage(),e);
        return ResultUtil.error(0,e.getMessage() == null? "网络异常，请稍后重试" : e.getMessage());
    }

}
