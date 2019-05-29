package com.xinaml.common.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.*;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @Author: [lgq]
 * @Date: [19-5-29 下午12:48]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class HystrixCommandAdvice {
    private String groupName;
    private String commandName;
    private String threadKey;
    private int timeOutSecond;
    private boolean timeOutEnable;

    public Object runCommand(final ProceedingJoinPoint pjp) {
        return wrapWithHystrixCommnad(pjp).execute();
    }

    private HystrixCommand<Object> wrapWithHystrixCommnad(final ProceedingJoinPoint pjp) {
        return new HystrixCommand<Object>(setter()) {
            @Override
            protected Object run() throws Exception {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    throw (Exception) throwable;
                }
            }

            @Override
            protected Object getFallback() {
                return "FALLBACK";
            }
        };
    }

    private HystrixCommand.Setter setter() {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupName))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandName))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadKey));
        HystrixCommandProperties.Setter commandPropertiesDefaults = HystrixCommandProperties.Setter().withExecutionTimeoutEnabled(timeOutEnable)
                .withExecutionTimeoutInMilliseconds(timeOutSecond * 1000);
        setter.andCommandPropertiesDefaults(commandPropertiesDefaults);
        return setter;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getThreadKey() {
        return threadKey;
    }

    public void setThreadKey(String threadKey) {
        this.threadKey = threadKey;
    }

    public int getTimeOutSecond() {
        return timeOutSecond;
    }

    public void setTimeOutSecond(int timeOutSecond) {
        this.timeOutSecond = timeOutSecond;
    }

    public boolean isTimeOutEnable() {
        return timeOutEnable;
    }

    public void setTimeOutEnable(boolean timeOutEnable) {
        this.timeOutEnable = timeOutEnable;
    }
}
