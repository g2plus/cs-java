package top.arhi.exception;

//SpringMVC实现全局异常处理器
//https://blog.csdn.net/hbtj_1216/article/details/81102063
//注解了 @ControllerAdvice 的类的方法可以使用 @ExceptionHandler、
// @InitBinder、 @ModelAttribute 注解到方法上，这对所有注解了 @RequestMapping 的控制器内的方法都有效。
//@ExceptionHandler：用于捕获所有控制器里面的异常，并进行处理

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.arhi.model.vo.ErrorResult;

/**
 * 自定义统一异常处理
 *  1、通过注解，声明异常处理类
 *  2、编写方法，在方法内部处理异常，构造响应数据
 *  3、方法上编写注解，指定此方法可以处理的异常类型
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handlerException(BusinessException be) {
        be.printStackTrace();
        ErrorResult errorResult = be.getErrorResult();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException1(Exception be) {
        be.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResult.error());
    }
}