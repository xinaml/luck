#luck

IDE: idea

JDK: 1.8.0_171

FRAME: 
        
        spring-cloud: Greenwich.SR1
        Spring-boot: 2.1.4.RELEASE
        Maven: 3.5.3
        
DOC:
    https://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/single/spring-cloud.html
    
    
    
启动顺序：

        1.eureka
        
        2.gateway
        
        3.其他服务
        
        （服务注册到eureka默认需要30秒）