package com.atguigu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author luzc
 * @date 2021/2/2 13:50
 * @desc q切面类
 * @Aspect 告诉spring 当前类是一个切面类
 *
 * @Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
 *
 * LogAspects 这个类叫切面
 * @Pointcut 这个叫做切点
 * ("execution(public int com.atguigu.aop.MathCalculator.*(..))") 这个叫做连接点
 * @Before 这个叫通知点
 *
 * target class: 原生bean
 * proxy： 代理对象，经过aop处理后新生成的对象
 *
 * aop容器每次在获取的时候，取到的就是代理对象而不是目标对象
 */

@Aspect
public class LogAspects {

    // 抽取公共的切入点表达式
    // 1、本类引用 ,直接 pointCut()
    // 2、其他的切面引用，则全类名+方法 com.atguigu.aop.LogAspects.pointCut()
    @Pointcut("execution(public int com.atguigu.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    // 传入的对象是切入表达式（指定在哪个方法切入）
    // 切入所有方法
//    @Before("com.atguigu.aop.MathCalculator.*()")
    // 切入该方法的任意参数
//    @Before("public int com.atguigu.aop.MathCalculator.div(..)")
    // 特定目标方法之前切入
    //@Before("public int com.atguigu.aop.MathCalculator.div(int ,int))
    @Before("pointCut()")
    // todo: jointPoint 这个参数一定要出现在参数表的第一位
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("方法名：" + joinPoint.getSignature().getName() +
                        "除法运行->@Before:参数列表是：{" +
                        Arrays.asList(args)+
                "}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("方法名：" + joinPoint.getSignature().getName() + "除法结束->@After");
    }

    @AfterReturning(value = "com.atguigu.aop.LogAspects.pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result) {
        System.out.println("方法名：" + joinPoint.getSignature().getName()+"除法正常返回->@AfterReturning运行结果是：{" + result +
                "}");
    }

    @AfterThrowing(value = "com.atguigu.aop.LogAspects.pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception) {

        System.out.println("方法名：" + joinPoint.getSignature().getName() +"除法异常->@AfterThrowing异常信息是：{" + exception.getMessage() +
                "}");
    }
}
