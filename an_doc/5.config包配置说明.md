可见如下的配置

    
    
    
    1.DateConverterConf：日期转换器
    
    2.ExceptionHandlerConf：异常处理器
    
    3.FeginInterceptorConf：token传递
    
    4.FeignConf：调用重试
    
    5.HystrixConf：断路器
    
    6.JSR303Conf：数据校验
    
    7.RibbonConf：负载均衡
    
    功能模块只是开启扫描，具体实现见继承的common父类
