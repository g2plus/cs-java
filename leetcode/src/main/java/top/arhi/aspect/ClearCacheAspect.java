package top.arhi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.arhi.annotation.ClearCache;
import top.arhi.model.pojo.Job;
import top.arhi.model.vo.AjaxResult;
import top.arhi.util.AsyncFactory;
import top.arhi.util.AsyncManager;
import top.arhi.util.RedisUtil;
import top.arhi.util.SpringElUtil;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ClearCacheAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(top.arhi.annotation.ClearCache)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Signature signature = proceedingJoinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method  = methodSignature.getMethod();
            Object[] args = proceedingJoinPoint.getArgs();
            // 获取Cache注解
            ClearCache annotation = method.getAnnotation(ClearCache.class);
            String keyId = annotation.name();
            // 支持spring El表达式
            keyId = SpringElUtil.parse(keyId, method, args);
            redisUtil.remove(keyId);
            Object proceed = proceedingJoinPoint.proceed();
            //使用线程池处理 500ms 执行任务
            AsyncManager.me().execute(AsyncFactory.execute(new Job("redisDelayDelete", "execute", new Object[]{keyId})), 500, TimeUnit.MILLISECONDS);
            return proceed;//返回业务代码的值
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return AjaxResult.error(999, "系统错误", null);
    }
}