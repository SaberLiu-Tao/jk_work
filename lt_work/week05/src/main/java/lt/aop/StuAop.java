package lt.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class StuAop {
    public void before(){
        System.out.println("begin ... ");
    }

    //后置通知
    public void after(){
        System.out.println("finish... ");
        System.out.println("finish... ");

    }

    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("    ====>around begin "); //1
        //调用process()方法才会真正的执行实际被代理的方法
        joinPoint.proceed();

        System.out.println("    ====>around finish "); //3
    }
}
